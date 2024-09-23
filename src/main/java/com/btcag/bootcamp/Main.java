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

        long botX = 7;
        long botY = 4;
        long x = 0;
        long y;
        long temp;
        boolean botHere;
        String seperator = "";
        while (x++ < 15) {
            seperator += "|---";
        }
        seperator += '|';
        String playfield;

        while (true) {
            playfield = seperator + "\n";
            y = 0;
            while (y < 10) {
                x = 0;
                while (x < 15) {
                    botHere = false;
                    temp = botX;
                    while (temp == x) {
                        while (botY == y) {
                            playfield += "| O ";
                            botHere = true;
                            botY--;
                            temp = -100;
                        }
                        temp--;
                    }
                    while (temp < -99) {
                        botY++;
                        temp = 0;
                    }
                    while (!botHere) {
                        playfield += "|   ";
                        botHere = true;
                    }
                    x++;
                }
                playfield += "| " + (y + 1) + "\n" + seperator + "\n";
                y++;
            }
            playfield += " ";
            x = 0;
            while (x++ < 15) {
                playfield += x;
                temp = ("" + x).length();
                while (4 - temp > 0) {
                    playfield += ' ';
                    temp++;
                }
            }
            System.out.println(playfield);
            System.out.println("Where do you want to move to?\nw: ↑   a: ←   s: ↓   d: →");
            char input = scanner.next().charAt(0);
            while (input == 'w') {
                input = ' ';
                y = botY - 1;
                while (y >= 0) {
                    while (y < 10) {
                        botY = y;
                        y = 10;
                    }
                    y = -1;
                }
            }
            while (input == 'a') {
                input = ' ';
                x = botX - 1;
                while (x >= 0) {
                    while (x < 15) {
                        botX = x;
                        x = 15;
                    }
                    x = -1;
                }
            }
            while (input == 's') {
                input = ' ';
                y = botY + 1;
                while (y >= 0) {
                    while (y < 10) {
                        botY = y;
                        y = 10;
                    }
                    y = -1;
                }
            }
            while (input == 'd') {
                input = ' ';
                x = botX + 1;
                while (x >= 0) {
                    while (x < 15) {
                        botX = x;
                        x = 15;
                    }
                    x = -1;
                }
            }
        }
    }
}