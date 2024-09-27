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

        long[] botPos = {7, 6};
        long[] size = {15, 10};
        long temp;
        String playfield;

        while (true) {
            playfield = generatePlayfield(size, botPos);
            System.out.println(playfield);

            do {
                System.out.println("\nWhere do you want to move to?\nw: ↑   a: ←   s: ↓   d: →");
            } while (!movePlayer(scanner.next().charAt(0), size, botPos));
        }

    }

    public static boolean movePlayer(char directionInput, long[] playfieldSize, long[] botPos) {
        long[] relPos = new long[2];
        switch (directionInput) {
            case 'w':
                relPos[1] = -1;
                break;
            case 'a':
                relPos[0] = -1;
                break;
            case 's':
                relPos[1] = 1;
                break;
            case 'd':
                relPos[0] = 1;
                break;
        }
        if (relPos[0] == 0 && relPos[1] == 0) {
            return false;
        }
        for (int i = 0; i < relPos.length; i++) {
            if (botPos[i] + relPos[i] < 0 || botPos[i] + relPos[i] >= playfieldSize[i]) {
                return false;
            }
        }
        botPos[0] += relPos[0];
        botPos[1] += relPos[1];
        return true;
    }

    public static String generatePlayfield(long[] size, long[] botPos) {
        String seperator = "";
        for (long i = 0; i < size[0]; i++) {
            seperator += "|---";
        }
        seperator += '|';
        String playfield;
        playfield = seperator + "\n";
        for (long y = 0; y < size[1]; y++) {
            for (long x = 0; x < size[0]; x++) {
                if (botPos[0] == x && botPos[1] == y) {
                    playfield += "| O ";
                } else {
                    playfield += "|   ";
                }
            }
            playfield += "| " + (y + 1) + "\n" + seperator + "\n";
        }
        playfield += ' ';
        for (long x = 0; x < size[0]; x++) {
            playfield += x;
            for (long i =  4 - ("" + x).length(); i > 0; i--) {
                playfield += ' ';
            }
        }
        return playfield;
    }
}