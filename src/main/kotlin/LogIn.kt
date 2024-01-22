
/*
 * Titel: Zeiterfassung MAIN
 * Firma: ABB TS
 * Autor: Nikola Djukic
 */

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
fun logIn() {
        do {
            // damit Ende eingeleitet werden kann
            var schleifenEnde   = false

            // Benutzer abfrage
            println("Bitte den Benutzer eingeben")
            val user = readln()

            // Passwort abfrage
            println("Bitte das Passwort für den Benutzer $user eingeben")
            var userpasswort = readln()

            val userDaten = LesenUserDB(user)                                 // Lesen der Benutzer Daten aus der Datenbank und speichern in userDaten
            //println(userDaten)                                             //um Ausgabe zu überprüfen

            val userDatenSplit = userDaten.split(" ")            // Splitet den String userDaten nach Abstand und speichert es in userDaten Split
            if (userDatenSplit.get(2) == user) {                           //überprüft ob der Benutername in Datenbank enthalten ist
                if (userDatenSplit.get(1) == userpasswort) {              //überprüft ob Passwirt in der Datenbank mit dem Benutzerpasswort übereinstimmt
                    schleifenEnde = true
                    println("Herzlich Wilkommen Benutzer: $user")
                    if (user == "Nikola") {
                        print("Master Of Univers")
                    }
                } else {
                    println("Falsches Passwort")
                }
            } else {
                println("Falscher Benutzername")
            }
        } while (schleifenEnde != true)                             //Bedingung damit Schleife weiter geführt wird
    }
