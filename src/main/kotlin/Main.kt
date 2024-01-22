
///*
// * Titel: Zeiterfassung MAIN
// * Firma: ABB TS
// * Autor: Nikola Djukic
// */
//

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
var userId = 0
var passwort = ""
var vorname = ""
var nachname = ""
var email = ""
var funktion = ""
var admin = ""

fun main() {

    do {
        // damit Ende eingeleitet werden kann
        var schleifenEndeLogIn   = false

        // Benutzer abfrage
        println("Bitte den Benutzer eingeben")
        val user = readln()

        // Passwort abfrage
        println("Bitte das Passwort für den Benutzer $user eingeben")
        var userpasswort = readln()

        userDaten = LesenUserDB(user)                                     // Lesen der Benutzer Daten aus der Datenbank und speichern in userDaten
        //println(userDaten)                                             //um Ausgabe zu überprüfen

        val userDatenSplit = SplitString(userDaten)                     // Splitet den String userDaten nach Abstand und speichert es in userDaten Split
        if (userDatenSplit.get(2) == user) {                           //überprüft ob der Benutername in Datenbank enthalten ist
            if (userDatenSplit.get(1) == userpasswort) {              //überprüft ob Passwirt in der Datenbank mit dem Benutzerpasswort übereinstimmt
                    schleifenEndeLogIn = true
                    println("Herzlich Wilkommen Benutzer: $user")
                    if (user == "Nikola") {
                        println("Master Of Univers")
                    }
                    menue(admin)
            }else {
                println("Falsches Passwort")
            }
        } else {
            println("Falscher Benutzername")
        }
    } while (schleifenEndeLogIn != true)                             //Bedingung damit Schleife weiter geführt wird
}







