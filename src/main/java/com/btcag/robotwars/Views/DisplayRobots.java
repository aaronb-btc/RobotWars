package com.btcag.robotwars.Views;

import com.btcag.robotwars.Api.model.Robot;

public class DisplayRobots {
    public static void display(Robot[] robots) {
        int counter = 0;
        for (Robot robot : robots) {
            if (counter > 0) {
                System.out.println();
            }

            System.out.print(++counter);
            System.out.println(':');

            System.out.print(robot.getName());
            System.out.print(" (");
            System.out.print(robot.getId());
            System.out.println("):");

            System.out.print("\tHealth...........");
            System.out.println(robot.getHealth());

            System.out.print("\tMovement rate....");
            System.out.println(robot.getMovementRate());

            System.out.print("\tAttack range.....");
            System.out.println(robot.getAttackRange());

            System.out.print("\tAttack damage....");
            System.out.println(robot.getAttackDamage());
        }
    }
}
