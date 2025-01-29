package com.btcag.robotwars.Services;

import com.btcag.robotwars.Api.ApiException;
import com.btcag.robotwars.Api.api.DefaultApi;
import com.btcag.robotwars.Api.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class APIFunctions {
    public static com.btcag.robotwars.Api.model.Robot[]
    getRobots() throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and store List of roboters
        List<com.btcag.robotwars.Api.model.Robot> robotList = api.apiRobotsGet();

        // Copy Robots from list to Array
        com.btcag.robotwars.Api.model.Robot[] robotArr = new com.btcag.robotwars.Api.model.Robot[robotList.size()];
        for (int i = 0; i < robotArr.length; i++) {
            robotArr[i] = robotList.get(i);
        }

        return robotArr;
    }

    public static com.btcag.robotwars.Api.model.Robot
    getRobot(String id) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and return roboter with specified ID
        return api.apiRobotsRobotIdGet(id);
    }

    public static com.btcag.robotwars.Api.model.Robot
    createRobot(NewRobot newRobot) throws ApiException {
        // Send Request and return answer
        return new DefaultApi().apiRobotsRobotPost(newRobot);
    }

    public static com.btcag.robotwars.Api.model.Robot
    createRobot(
            String name,
            BigDecimal health,
            BigDecimal attackDamage,
            BigDecimal attackRange,
            BigDecimal movementRate
    ) throws ApiException {
        NewRobot robot = new NewRobot();

        // Set properties of NewRobot
        robot.setName(name);
        robot.setHealth(health);
        robot.setAttackDamage(attackDamage);
        robot.setAttackRange(attackRange);
        robot.setMovementRate(movementRate);

        return createRobot(robot);
    }

    public static com.btcag.robotwars.Api.model.Robot
    createRobot(com.btcag.robotwars.Models.Robot robot) throws ApiException {
        // Pass robot's properties to function and return the output
        return createRobot(
                robot.getName(),
                new BigDecimal(robot.getHealth()),
                new BigDecimal(robot.getAttackDamage()),
                new BigDecimal(robot.getAttackRange()),
                new BigDecimal(robot.getMovementRate())
        );
    }

    public static Map<String, Object>[]
    getMaps() throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and store List of roboters
        List<Map> mapList = api.apiMapsGet();

        // Copy Robots from list to Array
        Map<String, Object>[] mapArr = new Map[mapList.size()];
        for (int i = 0; i < mapArr.length; i++) {
            mapArr[i] = mapList.get(i);
        }

        return mapArr;
    }

    public static Map
    getMap(String id) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and return map with specified ID
        return api.apiMapsMapIdGet(id);
    }

    public static Game
    getGame(String id) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and return game with specified ID
        return api.apiGamesGameIdGet(id);
    }

    public static Move[]
    getMoves(String gameId) throws ApiException{
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and store moves
        List<Move> moveList = api.apiGamesGameIdMoveGet(gameId);

        // Copy moves from List to array
        Move[] moveArr = new Move[moveList.size()];
        for (int i = 0; i < moveArr.length; i++) {
            moveArr[i] = moveList.get(i);
        }

        return moveArr;
    }

    public static Move[]
    getMovesAfter(String gameId, String moveId) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and store moves
        List<Move> moveList = api.apiGamesGameIdMoveMoveIdAfterGet(gameId, moveId);

        // Copy moves from List to array
        Move[] moveArr = new Move[moveList.size()];
        for (int i = 0; i < moveArr.length; i++) {
            moveArr[i] = moveList.get(i);
        }

        return moveArr;
    }

    public static Game
    createGame(String mapId) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Create NewGame with given mapId
        NewGame game = new NewGame();
        game.setMapId(mapId);

        // Send request and return answer
        return  api.apiGamesGamePost(game);
    }
    
    public static String
    joinGame(String robotId, String id) throws ApiException {
        // Create JoinGame object
        JoinGame join = new JoinGame();
        join.setRobotId(robotId);

        // Pass JoinGame object and id to function and return the output
        return joinGame(join, id);
    }

    public static String
    joinGame(JoinGame joinGame, String id) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and return playerId
        return api.apiGamesGameIdJoinPost(joinGame, id).getPlayerId();
    }

    public static Move
    makeMove(NewMove move, String gameId) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and return Move object
        return api.apiGamesGameIdMovePlayerPlayerIdPost(move, gameId, move.getPlayerId());
    }

    public static Move
    makeMove(
            String playerId,
            MovementType movementType,
            BigDecimal mapIndex,
            Align alignment,
            String gameId
    ) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Create NewMove object
        NewMove move = new NewMove();
        move.setPlayerId(playerId);
        move.setMovementType(movementType);
        move.setMapIndex(mapIndex);
        move.setAlign(alignment);

        // Pass NewMove object and gameId to function and return the output
        return makeMove(move, gameId);
    }
}