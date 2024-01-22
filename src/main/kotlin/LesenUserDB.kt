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
import java.time.LocalDateTime
import java.util.*

fun LesenUserDB(user: String) : String {
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
    val sql = "select  b2.userid, b2.passwort, b2.vorname, b2.nachname, b2.email, b2.funktion, b2.Admin from Benutzer b2 , Zeiterfassung z \n" +
            "where   b2.vorname  = '$user'"

    // SQL ausfuehren
    val data = statement.executeQuery(sql)
    var userId = 0
    // Zeilen ausgeben
    while (data.next()) {
        userId      = data.getInt("userid")
        passwort    = data.getString("passwort")
        vorname     = data.getString("vorname")
        nachname    = data.getString("nachname")
        email       = data.getString("email")
        funktion    = data.getString("funktion")
        admin       = data.getString("admin")

    //        println("Zeile: $userId | $passwort | $vorname | $nachname | $email | $funktion")         // Kontrolle Ausgabe
    }
    userIdString = userId.toString()

    return "$userIdString $passwort $vorname $nachname $email $funktion $admin"
}
