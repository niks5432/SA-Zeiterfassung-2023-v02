import java.time.Duration
import java.util.*

fun formatiereZeit (dauer: Duration): String {
    val stunden = dauer.toHours()
    val minuten = dauer.toMinutesPart()
    val sekunden = dauer.toSecondsPart()

    return "$stunden:$minuten:$sekunden"
}

fun SplitString(string: String): kotlin.collections.List<String> {
    val string = userDaten.split(" ")
    return string
}


fun zeitArchiv() {

}

fun zeiterfassungVisieren() {

}

fun benutzerErstellen()  {
    val scanner = Scanner(System.`in`)

// Generate automatic user ID
    val userId = 0

// Get user input
    print("Vorname: ")
    val vorname = scanner.nextLine()

    print("Nachname: ")
    val nachname = scanner.nextLine()

    print("Passwort: ")
    val passwort = scanner.nextLine()

    print("Email: ")
    val email = scanner.nextLine()

    print("Funktion: ")
    val funktion = scanner.nextLine()

    eintragBenutzerDb(userId, vorname, nachname, passwort, email, funktion)

}


fun berichteintrag() {

    println("Bitte heutige Arbeit zusammenfassen")
    val bericht = readln()
    val eientragid = lesenEintragIdDB()
    EintragBerichgtDB(bericht, eientragid)
}


