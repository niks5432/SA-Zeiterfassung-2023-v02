/*
 * Titel: Zeiterfassung MAIN
 * Firma: ABB TS
 * Autor: Nikola Djukic
 */

import java.sql.Time
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

fun main() {

    // Variablen Deklaration

    val currentDateTime = LocalDateTime.now()

    var buttonStart = 0
    var buttonPause = 1

    var startZeit           = LocalTime.now()
    var endZeit             = LocalTime.now()
    var pausenzeitBeginn    = LocalTime.now()
    var pausenzeitEnde      = LocalTime.now()


    // Schleife um die Zeiten für den heutigen Ta zu eruieren
    while (buttonStart <= 3) {

        // Einlesen welches ereigniss gerade stattfindet
        println("Badge Scanen")
        buttonStart = readln().toInt()

        // Bedingungen die ausgeführt werden nach dem Einlesen des ereignisses
        when (buttonStart) {
            1 -> {
                startZeit = LocalTime.now()
                startZeit = startZeit.withNano(0)

                println("Startzeit $startZeit")

            }

            4 -> {
                endZeit = LocalTime.now()
                endZeit = endZeit.withNano(0)

                println("Endzeit $endZeit")
            }

            2 -> {
                pausenzeitBeginn = LocalTime.now()
                pausenzeitBeginn = pausenzeitBeginn.withNano(0)

                println("pausenzeitBeginn $pausenzeitBeginn")
            }

            3 -> {
                pausenzeitEnde = LocalTime.now()
                pausenzeitEnde = pausenzeitEnde.withNano(0)

                println("pausenzeitEnde $pausenzeitEnde")
            }
        }
    }

    // Deklaration und ausrechnen der Arbeits & Pausenzeit
    var arbeitsZeit = Duration.between(startZeit,endZeit)
    var pausenZeit  = Duration.between(pausenzeitBeginn,pausenzeitEnde)

    // Mit ${} kan in println eine funktion aufgerufen werden
    println("Arbeitszeit: ${formatiereZeit(arbeitsZeit)} | Pausenzeit: ${formatiereZeit(pausenZeit)}")

    val zeiten = mutableListOf(startZeit, endZeit, formatiereZeit(pausenZeit))

    println(zeiten)


}

// Funktion damit die Zeit korrekt formatiert wird, weil Duration Methode einen ISO Datentypen zurückgibt
fun formatiereZeit (dauer: Duration): String {
    val stunden = dauer.toHours()
    val minuten = dauer.toMinutesPart()
    val sekunden = dauer.toSecondsPart()

    return "$stunden:$minuten:$sekunden"
}