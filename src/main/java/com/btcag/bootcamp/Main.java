package com.btcag.bootcamp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("  ____            _               _    __        __                     \n" +
                " |  _ \\    ___   | |__     ___   | |_  \\ \\      / /   __ _   _ __   ___ \n" +
                " | |_) |  / _ \\  | '_ \\   / _ \\  | __|  \\ \\ /\\ / /   / _` | | '__| / __|\n" +
                " |  _ <  | (_) | | |_) | | (_) | | |_    \\ V  V /   | (_| | | |    \\__ \\\n" +
                " |_| \\_\\  \\___/  |_.__/   \\___/   \\__|    \\_/\\_/     \\__,_| |_|    |___/\n\nEnter your username: ");
        String username = scanner.next();
        System.out.println("Welcome, " + username  + "!");

        long x = 0;
        long y = 0;
        String seperator = "";
        while (x++ < 15 * 4 + 1) {
            seperator += "-";
        }
        String playfield = seperator + "\n";
        while (y < 10) {
            x = 0;
            while (x < 15) {
                playfield += "|   ";
                x++;
            }
            playfield += "|\n" + seperator + "\n";
            y++;
        }
        System.out.print(playfield);
    }

}