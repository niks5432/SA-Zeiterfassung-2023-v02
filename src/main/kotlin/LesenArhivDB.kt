import java.sql.DriverManager
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date

fun lesenArchivDB(userId: Int) : String {
    // val currentDateTime = LocalDateTime.now()
    val PROTOCOL = "jdbc:mysql"
    val HOST =     "localhost"
    val PORT =     3306
    val DATABASE = "test7"
    val OPTIONS =  "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
    val URL =      "$PROTOCOL://$HOST:$PORT/$DATABASE?$OPTIONS"
    val USER =     "UserTest"
    val PASSWORD = "admin"

    // Verbindung zur DB herstellen
    val connection = DriverManager.getConnection(URL, USER, PASSWORD)

    // Statement erzeugen
    val statement = connection.createStatement()

    println("Geben Sie ein Startdatum im Format (yyyy-MM-dd) ein")
    // Startdatum von der Konsole lesen
    val startDatumStr = readlnOrNull()
    // Prüfen/Validierung des Datumsformats
    val startDatumFormat = SimpleDateFormat("yyyy-MM-dd")
    // Startdatum in ein Date-Objekt umwandeln (falls möglich)
    val startDatum: Date? = startDatumFormat.parse(startDatumStr)
    if (startDatum != null) {
        // Startdatum ohne Zeit ausgeben
        val formattedEndDatum = startDatumFormat.format(startDatum)
        println("Eingegebene Startdatum: $formattedEndDatum")
    } else {
        println("Ungültiges Datumsformat.")
    }

    println("Geben Sie ein Enddatum im Format (yyyy-MM-dd) ein")
    // Enddatum von der Konsole lesen
    val endDatumStr = readlnOrNull()
    // Prüfen/Validierung des Datumsformats
    val endDatumFormat = SimpleDateFormat("yyyy-MM-dd")
    // Enddatum in ein Date-Objekt umwandeln (falls möglich)
    val endDatum: Date? = endDatumFormat.parse(endDatumStr)
    if (endDatum != null) {
        // Startdatum ohne Zeit ausgeben
        val formattedEndDatum = endDatumFormat.format(endDatum)
        println("Eingegebene Enddatum: $formattedEndDatum")
    } else {
        println("Ungültiges Datumsformat.")
    }
    val endDatumParce = LocalDate.parse(endDatumStr)
    val startDatumParce = LocalDate.parse(startDatumStr)


    var anzahlArbeitstage = 0
    if (startDatum != null && endDatum != null) {
        anzahlArbeitstage = berechneArbeitstage(startDatumParce, endDatumParce)
//        println("Anzahl der Arbeitstage zwischen $startDatum und $endDatum: $anzahlArbeitstage")
    } else {
        println("Ungültiges Datumsformat.")
    }


    // SQL erstellen um Zeilen aus DB zu laden
    val sql = "SELECT b.userid, z.datum, z.startzeit, z.endzeit, b.vorname " +
            "FROM Zeiterfassung z, benutzer b " +
            "WHERE z.datum BETWEEN '$startDatumStr' AND '$endDatumStr' " +
//            "AND z.startzeit BETWEEN '$startTimeStr' AND  '$endTimeStr' " +
            "AND b.userid = '$userId'"



    // SQL ausfuehren
    val data = statement.executeQuery(sql)

    var totalArbeitszeit: Duration = Duration.ZERO
    val sollStundenAmTag: Duration = Duration.ofHours(8)
    val sollStundenAmTagAnzeige = 8
    var formatiertetotalArbeitszeit = ""

    //var formatierteSollStundenAmTag = formatiereZeit(sollStundenAmTag)
    val sollStundenAmTagInt = sollStundenAmTag.seconds.toInt()
    val totalSollStunden = sollStundenAmTagInt  * anzahlArbeitstage /3600

    var minusstunden = Duration.ZERO
    var ueberstunden = Duration.ZERO
    var minusDauer = Duration.ZERO
    var ueberstundenDauer = Duration.ZERO
    // Zeilen ausgeben
    while (data.next()) {


        val datum = data.getDate("datum")
        val startzeit = data.getTime("startzeit")
        val endzeit = data.getTime("endzeit")
        val vorname = data.getString("vorname")


        val startZeit = startzeit.toLocalTime()
        val endZeit = endzeit.toLocalTime()



        val arbeitZeit = Duration.between(startZeit, endZeit)
//      val arbeitTagenTotal = Duration.between()



        val minimumArbeitszeit: Duration = Duration.ofSeconds(1)

        val differenzSollStunden: Duration = if (arbeitZeit < sollStundenAmTag) {
            // Berechnung der Minusstunden
            minusDauer = sollStundenAmTag.minus(arbeitZeit)
            minusstunden = minusstunden.plus(minusDauer.negated())
            Duration.ZERO
        } else {
            // Berechnung der Überstunden
            ueberstundenDauer = arbeitZeit.minus(sollStundenAmTag)
            ueberstunden = ueberstunden.plus(ueberstundenDauer)
            Duration.ZERO
        }


        val formatiertearbeitZeit = formatiereZeit(arbeitZeit)
        val formatiertedifferenzSollStundenminus = formatiereZeit(minusDauer)
        val formatiertedifferenzSollStundenplus = formatiereZeit(ueberstundenDauer)
        if (arbeitZeit >= minimumArbeitszeit){
            totalArbeitszeit += arbeitZeit
            formatiertetotalArbeitszeit  = formatiereZeit(totalArbeitszeit)
        }
        else{continue}



        println("UserID: $userId | Datum: $datum | Startzeit: $startzeit | Endzeit: $endzeit | Vorname: $vorname")
        println("Arbeitszeit: $formatiertearbeitZeit, Sollstunden: $sollStundenAmTagAnzeige h , Differenz - $formatiertedifferenzSollStundenminus  + $formatiertedifferenzSollStundenplus")

    }
    println("Gesamtarbeitszeit: $formatiertetotalArbeitszeit ")
    println("Anzahl Arbeitstagen $anzahlArbeitstage, Gesamtsollstunden: $totalSollStunden, $minusstunden $ueberstunden")
    userIdString = userId.toString()

    return "$userIdString $passwort $vorname $nachname $email $funktion $admin"
}

fun berechneArbeitstage(startDatum: LocalDate, endDatum: LocalDate): Int {
    // Arbeitstage zählen, ohne Wochenenden
    val anzahlTage = ChronoUnit.DAYS.between(startDatum, endDatum) + 1
    val anzahlTageInt = anzahlTage.toInt()
    val wochenenden = (0..anzahlTage).map { startDatum.plusDays(it) }
        .count { it.dayOfWeek == DayOfWeek.SATURDAY || it.dayOfWeek == DayOfWeek.SUNDAY }
    return anzahlTageInt  - wochenenden
}
