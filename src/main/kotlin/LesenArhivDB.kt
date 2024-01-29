import java.sql.DriverManager
import java.time.LocalDate
import java.time.LocalDateTime
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import kotlin.time.toKotlinDuration
import kotlin.time.ExperimentalTime

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

    // Verbindung zur DB herstellen4
    val connection = DriverManager.getConnection(URL, USER, PASSWORD)

    // Statement erzeugen
    val statement = connection.createStatement()

    print("Geben Sie das Startdatum im Format (yyyy-MM-dd) ein: ")

    // Datum von der Konsole lesen
    val inputDateStr1 = readLine()

    // Datum in ein Date-Objekt umwandeln (falls möglich)
    val dateFormat1 = SimpleDateFormat("yyyy-MM-dd")

    try {
        val inputDate1: Date? = dateFormat1.parse(inputDateStr1)

        if (inputDate1 != null) {
            // Datum ohne Uhrzeit ausgeben
            val formattedDate1 = dateFormat1.format(inputDate1)
            println("Eingegebenes Datum: $formattedDate1")
        } else {
            println("Ungültiges Datumsformat.")
        }
    } catch (e: Exception) {
        println("Fehler beim Parsen des Datums: ${e.message}")
    }

    print("Geben Sie das Enddatum im Format (yyyy-MM-dd) ein: ")

    // Datum von der Konsole lesen
    val inputDateStr2 = readLine()

    // Datum in ein Date-Objekt umwandeln (falls möglich)
    val dateFormat2 = SimpleDateFormat("yyyy-MM-dd")

    try {
        val inputDate2: Date? = dateFormat1.parse(inputDateStr1)

        if (inputDate2 != null) {
            // Datum ohne Uhrzeit ausgeben
            val formattedDate2 = dateFormat2.format(inputDate2)
            println("Eingegebenes Datum: $formattedDate2")
        } else {
            println("Ungültiges Datumsformat.")
        }
    } catch (e: Exception) {
        println("Fehler beim Parsen des Datums: ${e.message}")
    }

    print("Geben Sie eine Startuhrzeit im Format (HH:mm:ss) ein: ")

    // Uhrzeit von der Konsole lesen
    val inputTimeStr1 = readLine()

    // Uhrzeit in ein Date-Objekt umwandeln (falls möglich)
    val timeFormat1 = SimpleDateFormat("HH:mm:ss")

    try {
        val inputTime1: Date? = timeFormat1.parse(inputTimeStr1)

        if (inputTime1 != null) {
            // Uhrzeit ohne Datum ausgeben
            val formattedTime1 = timeFormat1.format(inputTime1)
            println("Eingegebene Enduhrzeit: $formattedTime1")
        } else {
            println("Ungültiges Uhrzeitformat.")
        }
    } catch (e: Exception) {
        println("Fehler beim Parsen der Uhrzeit: ${e.message}")
    }

    print("Geben Sie eine Startuhrzeit im Format (HH:mm:ss) ein: ")

    // Uhrzeit von der Konsole lesen
    val inputTimeStr2 = readLine()

    // Uhrzeit in ein Date-Objekt umwandeln (falls möglich)
    val timeFormat2 = SimpleDateFormat("HH:mm:ss")

    val inputTime2: Date? = timeFormat2.parse(inputTimeStr2)

    if (inputTime2 != null) {
        // Uhrzeit ohne Datum ausgeben
        val formattedTime2 = timeFormat2.format(inputTime2)
            println("Eingegebene Enduhrzeit: $formattedTime2")
    } else {
            println("Ungültiges Uhrzeitformat.")
    }



    // SQL erstellen um Zeilen aus DB zu laden
    val sql = "SELECT b.userid, z.datum, z.startzeit, z.endzeit, b.vorname " +
            "FROM Zeiterfassung z, benutzer b " +
            "WHERE z.datum BETWEEN '$inputDateStr1' AND '$inputDateStr2' " +
            "AND z.startzeit BETWEEN '$inputTimeStr1' AND  '$inputTimeStr2' " +
            "AND b.userid = '$userId'"

//    val arbeitsZeit2 = Duration.between(inputDateStr1,inputDateStr2)
//    var pausenZeit2  = Duration.between(pausenzeitBeginn,pausenzeitEnde)

    // SQL ausfuehren
    val data = statement.executeQuery(sql)

    var totalArbeitszeit: Duration = Duration.ZERO

    // Zeilen ausgeben
    while (data.next()) {

        val datum = data.getDate("datum")
        val startzeit = data.getTime("startzeit")
        val endzeit = data.getTime("endzeit")
        val vorname = data.getString("vorname")



        val startZeit = startzeit.toLocalTime()
        val endZeit = endzeit.toLocalTime()

        val arbeitZeit = Duration.between(startZeit, endZeit)
        val minimumarbeitzeit : Duration = Duration.ofSeconds(1)

        if (arbeitZeit >= minimumarbeitzeit){
            totalArbeitszeit += arbeitZeit
        }
        else{continue}



        println("UserID: $userId | Datum: $datum | Startzeit: $startzeit | Endzeit: $endzeit | Vorname: $vorname")
        println("Arbeitszeit: $arbeitZeit")
        val total = kotlin.time.Duration

    }
    println("Gesamtarbeitszeit: $totalArbeitszeit")
    userIdString = userId.toString()

    return "$userIdString $passwort $vorname $nachname $email $funktion $admin"
}