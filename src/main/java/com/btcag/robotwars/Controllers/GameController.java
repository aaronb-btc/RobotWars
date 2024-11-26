package com.btcag.robotwars.Controllers;

import com.btcag.robotwars.Enums.Direction;
import com.btcag.robotwars.Models.*;
import com.btcag.robotwars.Views.*;


public class GameController {
    public static void main(String[] args) {
        IntroScreenView.display();
        Battlefield battlefield = new Battlefield(20 ,15);
        Robot playerRobot = createPlayerRobot(10);
        Robot enemyRobot = createEnemyRobot();

        Robot winner = null;
        Robot[] robots = {playerRobot, enemyRobot};
        int[] relPos;

        while (winner == null) {
            for (int move=1; move <= playerRobot.getMovementRate(); move++) {
                BattlefieldView.display(robots, battlefield);
                System.out.println("Move " + move + " out of " + playerRobot.getMovementRate());
                do {
                    relPos = directionToRelPos(MoveRobotView.display());
                } while (
                        !battlefield.isValidField(
                                playerRobot.getX() + relPos[0],
                                playerRobot.getY() + relPos[1]
                        )
                );
                playerRobot.moveRel(relPos[0], relPos[1]);
            }

            if (isWithinAttachRange(playerRobot, enemyRobot) && !enemyRobot.isKnockedOut()) {
                enemyRobot.takeDamage(playerRobot.getAttackDamage());
                System.out.println("You attacked " + enemyRobot.getName());
                if (enemyRobot.isKnockedOut()) {
                    System.out.println(enemyRobot.getName() + " is knocked out");
                    DisplayWinnerView.display(playerRobot);
                } else {
                    System.out.println(enemyRobot.getName() + " is down to " + enemyRobot.getHealth() + "health points");
                }
            }
        }
    }

    // For testing, same as System.out.print(Arrays.toString(array))
    private static void printArray(int[] array) {
        System.out.print('[');
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(',');
                System.out.print(' ');
            }
        }
        System.out.println(']');
    }

    private static int[] directionToRelPos(Direction direction) {
        return new int[] {
                (int)  Math.round(Math.sin(Math.toRadians(45 * direction.ordinal()))),
                (int) -Math.round(Math.cos(Math.toRadians(45 * direction.ordinal())))
        };
    }

    private static boolean isWithinAttachRange(Robot attacker, Robot victim) {
        return
                Math.abs(attacker.getX() - victim.getX()) <= attacker.getAttackRange() &&
                Math.abs(attacker.getY() - victim.getY()) <= attacker.getAttackRange();
    }

    private static Robot createEnemyRobot() {
        return new Robot(
                18,
                13,
                "Enemy",
                'X',
                3,
                1,
                1,
                7
        );
    }

    private static Robot createPlayerRobot(int skillPoints) {
        String[] skills = {"Movement rate", "Attack range", "Attack Damage", "Health"};
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
        System.out.println();
        String name = AskRobotNameView.display();
        return new Robot(
                0,
                0,
                name,
                name.charAt(0),
                skillPointsList[0],
                skillPointsList[1],
                skillPointsList[2],
                skillPointsList[3]
        );
    }
}