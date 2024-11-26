package com.btcag.robotwars.Controllers;

import java.sql.*;
import java.util.Scanner;

public class SQLiteConnector {


    private static final String URL = "jdbc:sqlite:C:/Users/AABLESSM/sqlite-tools/Bootcamp2024.db";

    public static void main(String[] args) {
        connect();
        //createTable();
        //insertUser("Joe Mama", "SafePasswd123");
        //insertUserFromUserInput();
        selectAllUsers();
        loginFromUserInput();
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Verbindung zur SQLite-Datenbank hergestellt.");
        } catch (SQLException e) {
            System.out.println("Verbindung zur SQLite-Datenbank fehlgeschlagen.");
            e.printStackTrace();
        }
        return conn;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS User (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "username TEXT NOT NULL, " + "password TEXT NOT NULL);";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabelle 'User' wurde erfolgreich erstellt.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle 'User'.");
            e.printStackTrace();
        }
    }

        public static void insertUser(String username, String password) {
        String sql = "INSERT INTO User(username, password) VALUES(?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("User erfolgreich eingefügt.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen des Users.");
            e.printStackTrace();
        }
    }

    public static void selectAllUsers() {
        String sql = "SELECT * FROM User";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + "\t" +
                        "Username: " + rs.getString("username") + "\t" +
                        "Password: " + rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen der Users.");
            e.printStackTrace();
        }
    }

    public static void insertUserFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        do {
            System.out.print("Gib den Benutzernamen ohne SQL Injection ein: ");
            username = scanner.nextLine();
        } while (username.length() < 2);
        System.out.print("Gib das Passwort ohne SQL Injection ein: ");
        password = scanner.nextLine();
        scanner.close();
        String sql = "INSERT INTO User(username, password) VALUES(?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("User erfolgreich eingefügt.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen des Users.");
            e.printStackTrace();
        }
    }

    public static void loginFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;

        System.out.print("Gib den Benutzernamen ohne SQL Injection ein: ");
        username = scanner.nextLine();

        System.out.print("Gib das Passwort ohne SQL Injection ein: ");
        password = scanner.nextLine();
        scanner.close();

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT true FROM User WHERE Username = ? AND Password = ?"
                )
        ) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            if (pstmt.executeQuery().getBoolean(1)) {
                System.out.println(username + " ist angemeldet.");
            } else {
                System.out.println("Ungültiger Benutzernamer oder falsches Passwort.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen der Users.");
            e.printStackTrace();
        }
    }
}
