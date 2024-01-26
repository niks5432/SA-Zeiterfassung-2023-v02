import java.sql.DriverManager
import java.util.*


fun eintragBenutzerDb(userIdNew: Int, passwortNew: String, vornameNew: String, nachnameNew: String, emailNew: String, funktionNew: String) {

// Database connection parameters
    val PROTOCOL = "jdbc:mysql"
    val HOST = "localhost"
    val PORT = 3306
    val DATABASE = "SA-Semesterarbeit-2023"
    val OPTIONS = "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
    val URL = "$PROTOCOL://$HOST:$PORT/$DATABASE?$OPTIONS"
    val USER = "UserTest"
    val PASSWORD = "admin"

// Verbindung zur DB herstellen
    val connection = DriverManager.getConnection(URL, USER, PASSWORD)

// Statement erzeugen
    val statement = connection.createStatement()


// SQL erstellen um Daten in DB zu speichern
    val sql = """INSERT INTO Benutzer (
                     userid,
                     passwort,
                     vorname,
                     nachname,
                     email,
                     funktion)
                 VALUES (
                     '$userIdNew',
                     '$passwortNew',
                     '$vornameNew',
                     '$nachnameNew',
                     '$emailNew',
                     '$funktionNew'
                 )"""

// SQL ausführen
    statement.executeUpdate(sql)
    println("Daten in DB gespeichert.")

}