package com.btcag.robotwars.Views;

import com.btcag.robotwars.Models.Robot;

public class DisplayRobotStatsView {
    public static void display(Robot robot) {
        System.out.println(
                "Stats of " + robot.getName() + " (" + robot.getId() + "):\n\t" +
                    "Health..........." + robot.getHealth() + "\n\t" +
                    "Movement rate...." + robot.getMovementRate() + "\n\t" +
                    "Attack range....." + robot.getAttackRange() + "\n\t" +
                    "Attack damage...." + robot.getAttackDamage() + "\n\t"
        );
    }
}
