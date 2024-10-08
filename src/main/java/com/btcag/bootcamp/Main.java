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

        long[] botPos = {1, 1};
        long[] size = {15, 10};
        long[][] enemyPositions = {{size[0] - 2, size[1] - 2}}; // May have to rewrite this so more bots are possible later
        int gameWon = 0;
        String playfield;

        while (gameWon == 0) {
            playfield = generatePlayfield(size, botPos, enemyPositions);
            System.out.println(playfield);

            do {
                System.out.println("\nWhere do you want to move to?\nw: ↑   a: ←   s: ↓   d: →");
            } while (!movePlayer(scanner.next().charAt(0), size, botPos)); // Repeats do codeblock if movement was invalid
            gameWon = gameWon(botPos, enemyPositions);
        }
        System.out.println(generatePlayfield(size, botPos, enemyPositions));
        // switch would be better mor more than 2 endings
        if (gameWon == 1) { // Player won
            System.out.println(username + " won!");
        } else if (gameWon == 2) { // Enemies won
            System.out.println(username + " was slain by the enemies.");
        }
    }

    public static int gameWon(long[] botPos, long[][] enemyPositions) {
        for (long[] enemyPos: enemyPositions) {
            if (botPos[0] == enemyPos[0] && botPos[1] == enemyPos[1]) {
                return 1; // player won
            } else {
                return 0; // nobody won
            } // enemies cant do anything (including winning by accident) yet
        }
        return 0;
    }

    public static boolean movePlayer(char directionInput, long[] playfieldSize, long[] botPos) {
        long[] relPos = new long[2]; // new pos relative to current botPos
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
        if (relPos[0] == 0 && relPos[1] == 0) { // no move is also invalid for now
            return false;
        }
        for (int i = 0; i < relPos.length; i++) {
            if (botPos[i] + relPos[i] < 0 || botPos[i] + relPos[i] >= playfieldSize[i]) { // new pos outside playfield
                return false; // report move as invalid
            }
        }
        botPos[0] += relPos[0]; // Update botPos
        botPos[1] += relPos[1];
        return true; // report move as valid
    }

    public static String generatePlayfield(long[] size, long[] botPos, long[][] enemyPositions) {
        String seperator = "";
        for (long i = 0; i < size[0]; i++) {
            seperator += "|---";
        }
        seperator += '|';
        String playfield;
        playfield = seperator + "\n";
        boolean enemyHere;
        for (long y = 0; y < size[1]; y++) {
            for (long x = 0; x < size[0]; x++) {

                // looks for enemies in this spot
                enemyHere = false;
                for (long[] enemyPos: enemyPositions) {
                    if (enemyPos[0] == x && enemyPos[1] == y) {
                        enemyHere = true;
                    }
                }

                // Decides what the current (x | y) field looks like based on coordinates
                if (botPos[0] == x && botPos[1] == y) {
                    playfield += "| O ";
                } else if (enemyHere) {
                    playfield += "| X ";
                } else {
                    playfield += "|   ";
                }
            }
            playfield += "| " + (y + 1) + "\n" + seperator + "\n";
        }
        playfield += ' ';

        // This adds the column numbers at the bottom
        for (long x = 0; x < size[0]; x++) {
            playfield += x;
            for (long i =  4 - ("" + x).length(); i > 0; i--) {
                playfield += ' ';
            }
        }
        return playfield;
    }
}