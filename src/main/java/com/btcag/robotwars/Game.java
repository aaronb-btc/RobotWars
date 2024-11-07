package com.btcag.robotwars;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        displaySplashScreen();
        Robot playerBot = createRobotFromUserInput(scanner);
        Playfield playfield = new Playfield(15, 10);
        Robot enemy = new Robot(playfield.getWidth() - 2, playfield.getHeight() - 2, "Enemy", 'X', 3);
        Robot[] robots = {playerBot, enemy};

        char direction;
        int[] relPos;
        String playfieldOutput;
        Robot[] robotsOnField;
        boolean gameWon = false;

        while (!gameWon) {
            for (int move = 0; move < playerBot.getMovementRate(); move++) {
                playfieldOutput = playfield.getPlayfield(robots);
                System.out.println(playfieldOutput);

                robotsOnField = getRobotsOnField(playerBot.getX(), playerBot.getY(), robots);
                if (robotsOnField.length > 1) {
                    System.out.println("Robots on field X: " + (playerBot.getX() + 1) + " Y: " + (playerBot.getY() + 1) + " are fighting!");
                    System.out.println("Winner: " + getRandomBot(robotsOnField, random).getName());
                    gameWon = true;
                }

                if (!gameWon) {
                    do {
                        System.out.println(
                                "\nMove " + (move + 1) + " of " + playerBot.getMovementRate() + " allowed moves:\n" +
                                "Where do you want to move to?\n" +
                                "Q  W  E\n" +
                                "A YOU D\n" +
                                "Y  S  X"
                        );
                        direction = (scanner.nextLine().toLowerCase() + " ").charAt(0);
                        relPos = getRelPos(direction);
                    } while (
                            !"qeyxwasd".contains(direction + "") ||
                                    !isSpaceAvailable(relPos[0] + playerBot.getX(), relPos[1] + playerBot.getY(), playfield)
                    );
                    playerBot.moveRel(relPos[0], relPos[1]);
                }
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
            case 'q':
                return new int[] {-1, -1};
            case 'e':
                return new int[] {1, -1};
            case 'y':
                return new int[] {-1, 1};
            case 'x':
                return new int[] {1, 1};
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

    public static void displaySplashScreen() {
        System.out.println(
                "  ____            _               _    __        __                     \n" +
                " |  _ \\    ___   | |__     ___   | |_  \\ \\      / /   __ _   _ __   ___ \n" +
                " | |_) |  / _ \\  | '_ \\   / _ \\  | __|  \\ \\ /\\ / /   / _` | | '__| / __|\n" +
                " |  _ <  | (_) | | |_) | | (_) | | |_    \\ V  V /   | (_| | | |    \\__ \\\n" +
                " |_| \\_\\  \\___/  |_.__/   \\___/   \\__|    \\_/\\_/     \\__,_| |_|    |___/\n"
        );
    }

    public static Robot createRobotFromUserInput(Scanner scanner) {
        if (scanner instanceof Scanner) {
            scanner = new Scanner(System.in);
        }

        System.out.print("Enter your username: ");
        String name = scanner.nextLine();
        System.out.println("\nWelcome, " + name + "!");

        System.out.print("\nWhat character should represent your robot? ");
        char displayChar;
        do {
            displayChar = (scanner.nextLine() + "O").charAt(0);
        } while (displayChar == ' ');

        return new Robot(1, 1, name, displayChar, 3);
    }
}