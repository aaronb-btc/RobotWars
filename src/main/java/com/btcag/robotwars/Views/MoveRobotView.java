package com.btcag.robotwars.Views;

import com.btcag.robotwars.Api.model.*;
import com.btcag.robotwars.Enums.Direction;
import com.btcag.robotwars.Models.Map;
import com.btcag.robotwars.Models.Robot;
import com.btcag.robotwars.Services.Menu;

import java.math.BigDecimal;
import java.util.Scanner;

public class MoveRobotView {
    public static NewMove display(Robot robot, String playerId, Map map) {
        NewMove move = new NewMove();
        move.setAlign(robot.getAlignment());
        move.setPlayerId(playerId);
        move.setMapIndex(new BigDecimal(robot.getX() + robot.getY() * map.getMapSizeX()));

        System.out.println("Choose your action");
        MovementType[] options = new MovementType[] {
                MovementType.MOVE,
                MovementType.ATTACK,
                MovementType.ALIGN
        };
        move.setMovementType(options[new Menu(new String[] {
                options[0].name(),
                options[1].toString(),
                options[2].name()
        }).letUserChoose()]);

        switch (move.getMovementType()) {
            case MOVE -> {
                int[] coords;
                do {
                    coords = move();
                } while (!map.isValidField(robot.getX() + coords[0], robot.getY() + coords[1]));
                robot.move(coords[0], coords[1]);
                move.setMapIndex(new BigDecimal(robot.getX() + robot.getY() * map.getMapSizeX()));
            }
            case ALIGN -> {
                robot.setAlignment(align());
                move.setAlign(robot.getAlignment());
            }

            // 'case ATTACK' is handled on the server side (I hope)
        }

        return move;
    }

    private static Align align() {
        Scanner scanner = new Scanner(System.in);
        Align alignment = null;
        int index = -1;
        do {
            System.out.println(
                    "In which direction do you want to be aligned?\n" +
                            "Q  W  E\n" +
                            "A YOU D\n" +
                            "Y  S  X"
            );
            index = "WEDXSYAQ".indexOf((scanner.nextLine().toUpperCase() + " ").charAt(0));
            if (index != -1) {
                alignment = new Align[] {Align.N, Align.NE, Align.E, Align.SE, Align.S, Align.SW, Align.W, Align.NW}[index];
            }
        } while (index == -1);

        return alignment;
    }

    private static int[] move() {
        Scanner scanner = new Scanner(System.in);
        int index = -1;
        do {
            System.out.println(
                    "Where do you want to move to?\n" +
                            "Q  W  E\n" +
                            "A YOU D\n" +
                            "Y  S  X"
            );
            index = "WEDXSYAQ".indexOf((scanner.nextLine().toUpperCase() + " ").charAt(0));
        } while (index == -1);

        int[] coords = new int[]{
                (int) Math.round(Math.sin((double) index / 4 * Math.PI)),
                (int) Math.round(-Math.cos((double) index / 4 * Math.PI))
        };

        return coords;
    }
}