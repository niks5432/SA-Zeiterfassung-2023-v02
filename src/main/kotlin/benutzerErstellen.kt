import java.sql.DriverManager
import java.sql.*
import java.time.LocalDateTime
import java.util.*

fun benutzerErstellen() {
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

    // Database connection parameters
    val PROTOCOL = "jdbc:mysql"
    val HOST = "localhost"
    val PORT = 3306
    val DATABASE = "test7"
    val OPTIONS = "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
    val URL = "$PROTOCOL://$HOST:$PORT/$DATABASE?$OPTIONS"
    val USER =     "UserTest"
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
                     '$userId',
                     '$passwort',
                     '$vorname',
                     '$nachname',
                     '$email',
                     '$funktion'
                 )"""

    // SQL ausführen
    statement.executeUpdate(sql)
    println("Daten in DB gespeichert.")
}
