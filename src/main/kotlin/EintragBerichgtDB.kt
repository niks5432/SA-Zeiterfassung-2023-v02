import java.sql.DriverManager


fun EintragBerichgtDB(bericht: String, eientragid: Int ) {
        val PROTOCOL = "jdbc:mysql"
        val HOST =     "localhost"
        val PORT =     3306
        val DATABASE = "SA-Semesterarbeit-2023"
        val OPTIONS =  "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        val URL =      "$PROTOCOL://$HOST:$PORT/$DATABASE?$OPTIONS"
        val USER =     "UserTest"
        val PASSWORD = "admin"

        // Zu speichernde Daten
        val berichtId   =  0
        val userId      = userIdString.toInt()

        // Verbindung zur DB herstellen
        val connection= DriverManager.getConnection(URL, USER, PASSWORD)

        // Statement erzeugen
        val statement = connection.createStatement()

        // SQL erstellen um Daten in DB speichern
        val sql = """INSERT INTO Bericht (
                     berichtid,
                     eintragid,
                     userId,
                     bericht)
                 VALUES (
                     '$berichtId',
                     '$eientragid',
                     '$userId',
                     '$bericht'

                 )"""

        // SQL ausfuehren
        statement.executeUpdate(sql)
        println("Daten in DB gespeichert.")
    }







