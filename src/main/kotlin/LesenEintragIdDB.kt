/*
* Titel: RDB-Test
* Firma: ABB TS
* Autor: M. Bontognali
*
* Beschreibung:
* Daten via JDBC von MySQL DB laden.
* Verwendet die Bibliothek mysql-connector-j-8.2.0.jar.
*
* Achtung:
* MySQL Server muss in Betrieb sein.
*/

import java.sql.DriverManager
import java.time.*
import java.util.*
fun lesenEintragIdDB() : Int {

    // val currentDateTime = LocalDateTime.now()
        val PROTOCOL = "jdbc:mysql"
        val HOST =     "localhost"
        val PORT =     3306
        val DATABASE = "SA-Semesterarbeit-2023"
        val OPTIONS =  "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        val URL =      "$PROTOCOL://$HOST:$PORT/$DATABASE?$OPTIONS"
        val USER =     "UserTest"
        val PASSWORD = "admin"

        // Verbindung zur DB herstellen
        val connection = DriverManager.getConnection(URL, USER, PASSWORD)

        // Statement erzeugen
        val statement = connection.createStatement()

        // SQL erstellen um Zeilen aus DB zu laden
        val sql = "SELECT eintragid FROM Zeiterfassung z ORDER BY eintragid DESC LIMIT 1;"

        // SQL ausfuehren
        val data = statement.executeQuery(sql)
        var eintragid = 0
        // Zeilen ausgeben
        while (data.next()) {
            eintragid = data.getInt("eintragid")

            //        println("Zeile: $userId | $passwort | $vorname | $nachname | $email | $funktion")         // Kontrolle Ausgabe
        }

        return eintragid
    }


