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
import java.time.LocalDateTime
import java.util.*


fun DatenbankEintraZeit() {
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
    val userId      =   0
    val vorname     =   "Michi"
    val nachname    =   "Der"
    val passwort    =   "2580"
    val email       =   "michi.der@der.ch"
    val funktion    =   "Handlanger"

    // Verbindung zur DB herstellen
    val connection= DriverManager.getConnection(URL, USER, PASSWORD)

    // Statement erzeugen
    val statement = connection.createStatement()

    // SQL erstellen um Daten in DB speichern
    val sql = """INSERT INTO Benutzer (
                     userid,
                     passwort,
                     vorname,
                     nachname,
                     email,
                     funktion)
                 VALUES (
                     '$variableUserId',
                     '$variablePasswort',
                     '$variableVorname',
                     '$variableNachname',
                     '$variableEmail',
                     '$variableFunktion'
                 )"""

    // SQL ausfuehren
    statement.executeUpdate(sql)
    println("Daten in DB gespeichert.")
}


