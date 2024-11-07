package com.btcag.robotwars;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        Robot playerBot = new Robot(1, 1, getUsernameFromUser(scanner), 'O');
        Playfield playfield = new Playfield(15, 10);
        Robot enemy = new Robot(playfield.getWidth() - 2, playfield.getHeight() - 2, "Enemy", 'X');
        Robot[] robots = {playerBot, enemy};


        char direction;
        int[] relPos;
        String playfieldOutput;
        Robot[] robotsOnField;
        boolean gameWon = false;

        while (!gameWon) {
            playfieldOutput = playfield.getPlayfield(robots);
            System.out.println(playfieldOutput);

            robotsOnField = getRobotsOnField(playerBot.getX(), playerBot.getY(), robots);
            if (robotsOnField.length > 1) {
                System.out.println("Robots on field X: " + playerBot.getX() + " Y: " + playerBot.getY() + " are fighting!");
                System.out.println("Winner: " + getRandomBot(robotsOnField, random).getName());
                gameWon = true;
            }

            if (!gameWon) {
                do {
                    System.out.println("\nWhere do you want to move to?\nw: ↑   a: ←   s: ↓   d: →");
                    direction = (scanner.nextLine() + " ").charAt(0);
                    relPos = getRelPos(direction);
                } while (
                        !"wasd".contains(direction + "") ||
                        !isSpaceAvailable(relPos[0] + playerBot.getX(), relPos[1] + playerBot.getY(), playfield)
                );
                playerBot.moveRel(relPos[0], relPos[1]);
            }
        }
    }

    public static Robot getRandomBot(Robot[] robots, Random random) {
        if (!(random instanceof Random)) {
            random = new Random();
        }
        return robots[random.nextInt(0, robots.length)];
    }

    public static Robot[] getRobotsOnField(int x, int y, Robot[] robots) {
        int numRobots = 0;
        for (Robot robot: robots) {
            if (robot.getX() == x && robot.getY() == y) {
                numRobots++;
            }
        }

        Robot[] robotsOnField = new Robot[numRobots];
        for (Robot robot: robots) {
            if (robot.getX() == x && robot.getY() == y) {
                robotsOnField[robotsOnField.length - numRobots] = robot;
                numRobots--;
            }
        }

        return robotsOnField;
    }

    public static boolean isSpaceAvailable(int x, int y, Playfield playfield) {
        return !(
            x < 0 ||
            y < 0 ||
            x >= playfield.getWidth() ||
            y >= playfield.getHeight()
        ); // Returns if space exists (true) or not (false)
    }

    public static int[] getRelPos(char directionInput) {
        switch (directionInput) {
            case 'w':
                return new int[] {0, -1};
            case 'a':
                return new int[] {-1, 0};
            case 's':
                return new int[] {0, 1};
            case 'd':
                return new int[] {1, 0};
        }
        return new int[] {0, 0};
    }

    public static String getUsernameFromUser(Scanner scanner) {
        if (scanner instanceof Scanner) {
            scanner = new Scanner(System.in);
        }

        System.out.print("  ____            _               _    __        __                     \n" +
                " |  _ \\    ___   | |__     ___   | |_  \\ \\      / /   __ _   _ __   ___ \n" +
                " | |_) |  / _ \\  | '_ \\   / _ \\  | __|  \\ \\ /\\ / /   / _` | | '__| / __|\n" +
                " |  _ <  | (_) | | |_) | | (_) | | |_    \\ V  V /   | (_| | | |    \\__ \\\n" +
                " |_| \\_\\  \\___/  |_.__/   \\___/   \\__|    \\_/\\_/     \\__,_| |_|    |___/\n\n" +
                "Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Welcome, " + username + "!");

        return username;
    }
}