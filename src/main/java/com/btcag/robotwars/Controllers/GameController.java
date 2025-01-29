package com.btcag.robotwars.Controllers;

import com.btcag.robotwars.Api.ApiException;
import com.btcag.robotwars.Api.model.*;
import com.btcag.robotwars.Enums.MapItemType;
import com.btcag.robotwars.Models.Map;
import com.btcag.robotwars.Models.MapItem;
import com.btcag.robotwars.Models.Robot;
import com.btcag.robotwars.Services.APIFunctions;
import com.btcag.robotwars.Views.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

import static com.btcag.robotwars.Services.PosMod.posMod;


public class GameController {
    public static void main(String[] args) throws ApiException, InterruptedException {
        IntroScreenView.display();
        Robot playerRobot = new Robot(
                0,
                0,
                getRobotFromUser(16)
        );
        System.out.println();
        Game game = getGameFromUser();
        Map map = new Map(APIFunctions.getMap(game.getMap()));
        final boolean isHost = game.getPlayer().isEmpty();

        // Assigns MapItems to player and enemy
        MapItem playerMapItem = null;
        MapItem enemyMapItem;
        for (MapItem mapItem : isHost ? map.getMapItems() : map.getMapItems().reversed()) {
            if (mapItem.getMapItemType() == MapItemType.ROBOT) {
                if (playerMapItem == null) {
                    playerMapItem = mapItem;
                } else {
                    enemyMapItem = mapItem;
                    break;
                }
            }
        }

        System.out.println(playerRobot);
        System.out.println(game);
        System.out.println();

        String playerId = APIFunctions.joinGame(playerRobot.getId(), game.getId());

        // Tell user he's getting logged in, although he already joined the game
        // I'M JUST LYING TO SPARE HIM FROM REALITY
        System.out.print("""
                Game found.
                Joining game...
                """);

        // (actually waiting until new full 10 seconds so clients are synced)
        // this has a slim chance of working cause PCs are rarely synced perfectly
        Thread.sleep(10000 - posMod(System.currentTimeMillis(), 10000));

        int dotsAmount = 0;
        while (game.getStatus() != Game.StatusEnum.STARTED) {
            for (int i = 0; i < (isHost ? 10 : 0); i++) {
                dotsAmount = (int) posMod(dotsAmount, 3L) + 1;
                System.out.print("\rWaiting for 2nd player");
                System.out.print(".".repeat(dotsAmount));

                // Wait until new full second
                Thread.sleep(1000 - posMod(System.currentTimeMillis(), 1000));
            }

            // update game
            game = APIFunctions.getGame(game.getId());
        }
        System.out.println();

        // Get enemy's robot
        com.btcag.robotwars.Api.model.Robot enemyRobot = null;
        for (PlayerRobot player : game.getPlayer()) {
            if (!player.getPlayerId().equals(playerId)) {
                enemyRobot = APIFunctions.getRobot(player.getRobotId());
                break;
            }
        }
        assert enemyRobot != null;

        long movesMade = 0;
        long prevMovesMade = 0;
        String lastMoveId = null;

        while (game.getStatus() != Game.StatusEnum.END) {
            BattlefieldView.display(map);
            if (isMyTurn(isHost, playerRobot.getMovementRate(), enemyRobot.getMovementRate().intValue(), movesMade)) {
                NewMove move = MoveRobotView.display(playerRobot, playerId, map);
                APIFunctions.makeMove(move, game.getId());
                movesMade++;
            } else {
                while (movesMade == prevMovesMade) {
                    Thread.sleep(10000 - posMod(System.currentTimeMillis(), 10000));

                    // Increase movesMade if 2nd player has done his move
                    if (lastMoveId == null) {
                        if (APIFunctions.getMoves(game.getId()).length > 0) {
                            movesMade++;
                        }
                    } else if (APIFunctions.getMovesAfter(game.getId(), lastMoveId).length > 0) {
                        movesMade++;
                    }
                }
                prevMovesMade = movesMade;
            }

            // Get id of last move
            if (lastMoveId == null) {
                lastMoveId = APIFunctions.getMoves(game.getId())[(int) (movesMade - 1)].getId();
            } else {
                lastMoveId = APIFunctions.getMovesAfter(game.getId(), lastMoveId)[(int) movesMade - 1].getId();
            }
        }
    }

    public static boolean isMyTurn(
            boolean isHost,
            int playerMovementRate,
            int enemyMovementRate,
            long numOfMoves
    ) {
        return numOfMoves % 2 == (
                playerMovementRate > enemyMovementRate
                || playerMovementRate == enemyMovementRate && isHost
                ? 0 : 1
        );
    }

    public static int random(int min, int max) {
        return (int) Math.round(Math.random() * (max - min)) + min;
    }

    public static java.util.Map<String, Object> getMapFromUser() throws ApiException {
        Scanner scanner = new Scanner(System.in);

        // Request and store maps from API
        java.util.Map<String, Object>[] maps = APIFunctions.getMaps();

        System.out.println("Here are the existing Maps:");

        // print out Maps and their numbers
        DisplayMaps.display(maps);

        // Loops until map was 'acquired'
        int input;
        while (true) {
            System.out.println("Enter the maps' number to choose the map.");
            input = Integer.parseInt(scanner.nextLine().replace(" ", ""));

            if (input > 0 && input <= maps.length) {
                return maps[input - 1];
            }
        }
    }

    public static Game getGameFromUser() throws ApiException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                Enter a game's uuid
                An invalid entry will let you host your own game
                """);

        // Lets user join or host a game
        Game game;
        try {
            game = APIFunctions.getGame(scanner.nextLine().replace(" ", ""));
        } catch (ApiException e) {
            System.out.println("Select the map for your game");
            game = APIFunctions.createGame(
                    new Map(
                            getMapFromUser()
                    ).getId()
            );
        }
        // Calls this function if game has status STARTED or END
        if (game.getStatus() != Game.StatusEnum.INITIAL) {
            System.out.println("""
                    Game has already been started.
                    Pick another game.
                    """);
            getGameFromUser();
        }

        return game;
    }

    public static com.btcag.robotwars.Api.model.Robot getRobotFromUser(int skillPoints) throws ApiException {
        Scanner scanner = new Scanner(System.in);
        com.btcag.robotwars.Api.model.Robot[] robots = APIFunctions.getRobots();

        // Lets user choose between existing or new robot
        System.out.println("Here are the existing Roboters:");
        DisplayRobots.display(robots);
        System.out.println();
        System.out.print(robots.length + 1);
        System.out.print("""
                :
                Create your own robot
                """);

        // Loops until user has chosen or created a robot
        int input;
        while (true) {
            System.out.println("Enter a robot's number to choose the robot.");
            input = Integer.parseInt(scanner.nextLine().replace(" ", ""));
            if (input == robots.length + 1) {
                System.out.println();
                return APIFunctions.createRobot(createPlayerRobot(skillPoints));
            }

            if (input > 0 && input <= robots.length) {
                return robots[input - 1];
            }
        }
    }

    public static NewRobot createPlayerRobot(int skillPoints) {
        // Asks user for name
        String name = AskRobotNameView.display();
        String[] skills = {"Health", "Attack Damage", "Attack Range", "Movement Rate"};

        // Shows user skills
        System.out.println("These are your available Skills:");
        for (String skill: skills) {
            System.out.println(skill);
        }

        // Asks user for each skill
        int[] skillPointsList = new int[skills.length];
        for (int i = 0; i < skills.length; i++) {
            System.out.println();
            skillPointsList[i] = AskSkillPointView.display(skills[i], skillPoints);
            skillPoints -= skillPointsList[i];
        }

        // Creates NewRobot with attributes specified by the user
        NewRobot robot = new NewRobot();
        robot.setName(name);
        robot.setHealth(new BigDecimal(skillPointsList[0]));
        robot.setAttackDamage(new BigDecimal(skillPointsList[1]));
        robot.setAttackRange(new BigDecimal(skillPointsList[2]));
        robot.setMovementRate(new BigDecimal(skillPointsList[3]));

        return robot;
    }
}