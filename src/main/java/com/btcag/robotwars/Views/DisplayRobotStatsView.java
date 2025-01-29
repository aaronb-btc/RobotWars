package com.btcag.robotwars.Views;

import com.btcag.robotwars.Models.Robot;

public class DisplayRobotStatsView {
    public static void display(Robot robot) {
        System.out.print("Skills of ");
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
