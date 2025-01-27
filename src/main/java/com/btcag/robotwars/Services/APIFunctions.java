package com.btcag.robotwars.Services;

import com.btcag.robotwars.Api.ApiException;
import com.btcag.robotwars.Api.api.DefaultApi;
import com.btcag.robotwars.Api.model.JoinGame;
import com.btcag.robotwars.Api.model.JoinGameResponse;
import com.btcag.robotwars.Api.model.NewGame;
import com.btcag.robotwars.Api.model.NewRobot;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.Join;

import java.math.BigDecimal;
import java.util.List;

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

        // Send Request and return answer
        return new DefaultApi().apiRobotsRobotPost(robot);
    }

    public static com.btcag.robotwars.Api.model.Robot
    createRobot(com.btcag.robotwars.Models.Robot r) throws ApiException {
        // Pass robot's properties to function and return the output
        return createRobot(
                r.getName(),
                new BigDecimal(r.getHealth()),
                new BigDecimal(r.getAttackDamage()),
                new BigDecimal(r.getAttackRange()),
                new BigDecimal(r.getMovementRate())
        );
    }

    public static java.util.Map[]
    getMaps() throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();

        // Request and store List of roboters
        List<java.util.Map> mapList = api.apiMapsGet();

        // Copy Robots from list to Array
        java.util.Map[] mapArr = new java.util.Map[mapList.size()];
        for (int i = 0; i < mapArr.length; i++) {
            mapArr[i] = mapList.get(i);
        }
        return mapArr;
    }

    public static java.util.Map
    getMap(String id) throws ApiException {
        DefaultApi api = new DefaultApi();

        return api.apiMapsMapIdGet(id);
    }

    public static com.btcag.robotwars.Api.model.Game
    getGame(String id) throws ApiException {
        // Open API object
        DefaultApi api = new DefaultApi();
        // Request and return game with specified ID
        return api.apiGamesGameIdGet(id);
    }

    public static com.btcag.robotwars.Api.model.Game
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
        // Open API object
        DefaultApi api = new DefaultApi();

        // Create NewJoin with given robotId
        JoinGame join = new JoinGame();
        join.setRobotId(robotId);

        // Request and return playerId
        return api.apiGamesGameIdJoinPost(join, id).getPlayerId();
    }
}