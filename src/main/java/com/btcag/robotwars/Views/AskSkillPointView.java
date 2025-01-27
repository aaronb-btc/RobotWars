package com.btcag.robotwars.Views;

import java.util.Scanner;

public class AskSkillPointView {
    public static int display(String skillName, int remainingSkillPoints) {
        int amountPoints = 0;
        if (remainingSkillPoints > 0) {
            Scanner scanner = new Scanner(System.in);

            System.out.println(
                    "How many of your " + remainingSkillPoints + " remaining skill points\n" +
                    "do you want to use for " + skillName + "?"
            );
            amountPoints = scanner.nextInt();
            while (amountPoints > remainingSkillPoints) {
                System.out.println("You only have " + remainingSkillPoints + " left.\nPlease enter another number:");
                amountPoints = scanner.nextInt();
            }
        } else {
            System.out.println(
                    "Since you don't have any skill points left,\n" +
                    "the " + skillName + " skill was set to 0 points."
            );
        }

        return amountPoints;
    }
}