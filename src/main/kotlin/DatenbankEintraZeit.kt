/*
 * Titel: RDB-Test
 * Firma: ABB TS
 * Autor: M. Bontognali
 *
 * Beschreibung:
 * Daten via JDBC von MySQL DB speichern.
 * Verwendet die Bibliothek mysql-connector-j-8.2.0.jar.
 *
 * Einrichten der Datenbank:
 * create database people;
 * use people;
 * CREATE TABLE tabPerson (
 *   name VARCHAR(50) NOT NULL,
 *   age INTEGER
 * );
 *
 * Achtung:
 * MySQL Server muss in Betrieb sein.
 */

import java.sql.DriverManager
import java.sql.*
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


//fun main () {
//    var userId      = 1
//    var startzeit : LocalTime
//    startzeit   = 9:00;00.00
//
//
//}



fun DatenbankEintraZeit(userId: Int, startzeit: LocalTime, endzeit: LocalTime, pausenzeit: String, abwesenheitsid: Int, zustandid: Int ) {
    val currentDateTime = LocalDateTime.now()
    val PROTOCOL = "jdbc:mysql"
    val HOST =     "localhost"
    val PORT =     3306
    val DATABASE = "SA-Semesterarbeit-2023"
    val OPTIONS =  "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
    val URL =      "$PROTOCOL://$HOST:$PORT/$DATABASE?$OPTIONS"
    val USER =     "UserTest"
    val PASSWORD = "admin"

    // Zu speichernde Daten
    val eintragid       =  0
    val datum           =  LocalDate.now()



    // Verbindung zur DB herstellen
    val connection= DriverManager.getConnection(URL, USER, PASSWORD)

    // Statement erzeugen
    val statement = connection.createStatement()

    // SQL erstellen um Daten in DB speichern
    val sql = """INSERT INTO Zeiterfassung (
                     eintragid,
                     userId,
                     datum,
                     startzeit,
                     endzeit,
                     pausenzeit,                 
                     abwesenheitsid,
                     zustandid)
                 VALUES (
                     '$eintragid',
                     '$userId',
                     '$datum',
                     '$startzeit',
                     '$endzeit',
                     '$pausenzeit',
                     '$abwesenheitsid',
                     '$zustandid'
                 )"""

    // SQL ausfuehren
    statement.executeUpdate(sql)
    println("Daten in DB gespeichert.")
}


