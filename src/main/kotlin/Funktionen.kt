import java.time.Duration

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

fun benutzerErstellen() {


}


fun berichteintrag() {

    println("Bitte heutige Arbeit zusammenfassen")
    val Bericht = readln()
    EintragBerichgtDB()
}

fun EintragBerichgtDB() {


}
