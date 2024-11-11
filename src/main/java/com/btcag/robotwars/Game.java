package com.btcag.robotwars;

import java.util.Random;
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displaySplashScreen();
        Robot playerBot = createRobotFromUserInput(scanner);
        Playfield playfield = new Playfield(15, 10);
        Robot enemy = new Robot(playfield.getWidth() - 2, playfield.getHeight() - 2, "Enemy", 'X', 3, 1, 1, 7);
        Robot[] robots = {playerBot, enemy};

        char direction;
        int[] relPos;
        String playfieldOutput;
        Robot[] robotsInRange;
        boolean gameWon = false;

        while (!gameWon) {
            robotsInRange = getRobotsNearField(playerBot.getX(), playerBot.getY(), playerBot.getAttackRange(), robots);
            if (robotsInRange.length > 1) {
                for (Robot robot: robotsInRange) {
                    if (robot != playerBot && !robot.isKnockedOut()) {
                        attackRobot(playerBot, robot);
                        if (robot.isKnockedOut()) {
                            System.out.println("You knocked out " + robot.getName());
                            gameWon = checkGameWon(playerBot, robots);

                        } else {
                            System.out.println("You attacked " + robot.getName() + ", he is down to " + robot.getHealth() + " hp.");
                        }
                    }
                }
            }

            for (int move = 0; move < playerBot.getMovementRate(); move++) {
                if (!gameWon) {
                    playfieldOutput = playfield.getPlayfield(robots);
                    System.out.println(playfieldOutput);

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
                    System.out.println();
                }
            }
        }
    }

    public static boolean checkGameWon(Robot potentialWinner, Robot[] robots) {
        int knockedOutRobotNum = 0;
        for (Robot enemyRobot: robots) {
            if (enemyRobot != potentialWinner && enemyRobot.isKnockedOut()) {
                knockedOutRobotNum++;
            }
        }

        return knockedOutRobotNum == (robots.length - 1);
    }

    public static void attackRobot(Robot attacker, Robot victim) {
        if (attacker instanceof Robot && victim instanceof Robot) {
            victim.takeDamage(attacker.getAttackDamage());
        }
    }

    public static Robot[] getRobotsNearField(int x, int y, int tolerance, Robot[] robots) {
        int numRobots = 0;
        for (Robot robot: robots) {
            if (
                    Math.abs(robot.getX() - x) <= tolerance &&
                    Math.abs(robot.getY() - y) <= tolerance
            ) {
                numRobots++;
            }
        }

        Robot[] robotsOnField = new Robot[numRobots];
        for (Robot robot: robots) {
            if (
                    Math.abs(robot.getX() - x) <= tolerance &&
                    Math.abs(robot.getY() - y) <= tolerance
            ) {
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
        if (!(scanner instanceof Scanner)) {
            scanner = new Scanner(System.in);
        }

        System.out.print("Enter your username: ");
        String name = scanner.nextLine();
        System.out.println("\nWelcome, " + name + '!');

        System.out.print("\nWhat character should represent your robot? ");
        char displayChar;
        do {
            displayChar = (scanner.nextLine() + 'O').charAt(0);
        } while (displayChar == ' ');

        return new Robot(1, 1, name, displayChar, 3, 3, 2, 5);
    }
}