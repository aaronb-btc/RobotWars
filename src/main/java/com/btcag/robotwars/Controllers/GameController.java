package com.btcag.robotwars.Controllers;

import com.btcag.robotwars.Api.ApiException;
import com.btcag.robotwars.Api.model.Game;
import com.btcag.robotwars.Enums.Direction;
import com.btcag.robotwars.Models.Map;
import com.btcag.robotwars.Models.Robot;
import com.btcag.robotwars.Services.APIFunctions;
import com.btcag.robotwars.Views.*;

import java.util.Arrays;
import java.util.Scanner;


public class GameController {
    public static void main(String[] args) throws ApiException, InterruptedException {
        IntroScreenView.display();
        Robot playerRobot = getRobotFromUser(10); // X, Y are 0, 0
        System.out.println();
        Game game = getGameFromUser();
        String playerId = APIFunctions.joinGame(playerRobot.getId(), game.getId());
        game = APIFunctions.getGame(game.getId());

        System.out.println(playerRobot.toString());
        System.out.println(game.toString());
        System.out.println("PlayerId: " + playerId);
        System.out.println();

        int dotsAmount = 0;

        while (game.getStatus() == Game.StatusEnum.INITIAL) {
            for (int i = 0; i < 10; i++) {
                dotsAmount = (int) posMod(dotsAmount, 3L) + 1;
                System.out.print("\rWaiting for 2nd player" + ".".repeat(dotsAmount));

                // Wait until new full second
                Thread.sleep(1000 - posMod(System.currentTimeMillis(), 1000));
            }
        }

        System.out.println();

        String winner = null;

        while (winner == null) {
            break;
        }
    }

    public static long posMod(long a, long b) {
        long result = a % b;
        return result + (result < 0 ? b : 0);
    }

    public static int random(int min, int max) {
        return (int) Math.round(Math.random() * (max - min)) + min;
    }

    public static boolean isRobotAligned(Robot attacker, Robot victim) {
        Direction direction = Direction.values()[attacker.getAlignment().ordinal()];
        int xDiff = victim.getX() - attacker.getX();
        int yDiff = victim.getY() - attacker.getY();
        return
                Math.abs(xDiff) * direction.x == xDiff &&
                Math.abs(yDiff) * direction.y == yDiff;
    }

    // Combine   ↓   and   ↑   to check if the attacker is able to hurt the victim

    private static boolean isWithinAttachRange(Robot attacker, Robot victim) {
        return
                Math.abs(attacker.getX() - victim.getX()) <= attacker.getAttackRange() &&
                Math.abs(attacker.getY() - victim.getY()) <= attacker.getAttackRange();
    }

    public static com.btcag.robotwars.Models.Map[] getMaps() throws ApiException {
        // Request and store maps from API
        java.util.Map<String, Object>[] apiMaps = APIFunctions.getMaps();

        // Convert and store maps to own more usable model
        com.btcag.robotwars.Models.Map[] maps = new com.btcag.robotwars.Models.Map[apiMaps.length];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new com.btcag.robotwars.Models.Map(apiMaps[i]);
        }

        return maps;
    }

    public static java.util.Map getMapFromUser() throws ApiException {
        Scanner scanner = new Scanner(System.in);

        // Request and store maps from API
        java.util.Map<String, Object>[] apiMaps = APIFunctions.getMaps();

        // Convert and store maps to own more usable model
        com.btcag.robotwars.Models.Map[] maps = new Map[apiMaps.length];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new com.btcag.robotwars.Models.Map(apiMaps[i]);
        }

        System.out.println("Here are the existing Maps:");

        // print out Maps and their numbers
        for (com.btcag.robotwars.Models.Map map : maps) {
            BattlefieldView.display(map);
            System.out.println(map.getId() + '\n');;
        }

        java.util.Map map = null;

        while (map == null) {
            try {
                System.out.println("Enter a maps' uuid");
                map = APIFunctions.getMap(scanner.nextLine());
            } catch (ApiException e) {}
        }

        return map;
    }

    public static Game getGameFromUser() throws ApiException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Enter a game's uuid\n" +
                "An invalid entry will let you Host your own game"
        );

        Game game;
        try {
            game = APIFunctions.getGame(scanner.nextLine());
        } catch (ApiException e) {
            System.out.println("Select the map for your game");
            game = APIFunctions.createGame(
                    new com.btcag.robotwars.Models.Map(
                            getMapFromUser()
                    ).getId()
            );
        }
        if (game.getStatus() != Game.StatusEnum.INITIAL) {
            System.out.println("Game has already been started.\n" +
                    "Pick another game.\n");
            getGameFromUser();
        }

        return game;
    }

    public static Robot getRobotFromUser(int skillPoints) throws ApiException {
        Scanner scanner = new Scanner(System.in);
        String robots = Arrays.toString(APIFunctions.getRobots());

        System.out.println("Here are the existing Roboters: \n" +
                robots.substring(1, robots.length() - 2) +
                "\nEnter a robot's uuid to choose the robot." +
                "\nAn invalid entry will let you create your own robot.");

        String input = scanner.nextLine();

        com.btcag.robotwars.Api.model.Robot robot;
        try {
            robot = APIFunctions.getRobot(input.replace(" ", ""));
        } catch (ApiException e) {
            System.out.println("No valid uuid. Create your robot:");
            robot = APIFunctions.createRobot(createPlayerRobot(skillPoints));
        }
        return new Robot(0, 0, robot);
    }

    public static Robot createPlayerRobot(int skillPoints) {
        String name = AskRobotNameView.display();
        String[] skills = {"Health", "Attack Damage", "Attack Range", "Movement Rate"};
        int[] skillPointsList = new int[skills.length];
        System.out.println("These are your available Skills:");
        for (String skill: skills) {
            System.out.println(skill);
        }
        for (int i = 0; i < skills.length; i++) {
            System.out.println();
            skillPointsList[i] = AskSkillPointView.display(skills[i], skillPoints);
            skillPoints -= skillPointsList[i];
        }

        return new Robot(
                0,
                0,
                "",
                name,
                skillPointsList[0], // Health
                skillPointsList[1], // Attack Damage
                skillPointsList[2], // Attack Range
                skillPointsList[3]  // Movement Rate
        );
    }
}