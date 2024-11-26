package com.btcag.robotwars.Views;

import com.btcag.robotwars.Models.Battlefield;
import com.btcag.robotwars.Models.Robot;

public class BattlefieldView {
    public static void display(Robot[] robots, Battlefield battlefield) {
        String seperator = "";
        for (long i = 0; i < battlefield.getWidth(); i++) {
            seperator += "|---";
        }
        seperator += '|';
        System.out.println(seperator);
        char myChar;
        for (long y = 0; y < battlefield.getHeight(); y++) {
            for (long x = 0; x < battlefield.getWidth(); x++) {
                myChar = ' ';
                for (Robot robot: robots) {
                    if (robot.getX() == x && robot.getY() == y) {
                        myChar = robot.getDisplayChar();
                        break;
                    }
                }
                System.out.print("| " + myChar + ' ');
            }
            System.out.println("| " + (y + 1) + "\n" + seperator);
        }
        System.out.print(' ');

        // This adds the column numbers at the bottom
        for (long x = 0; x < battlefield.getWidth(); x++) {
            System.out.print(x + 1);
            for (long i =  4 - ("" + x).length(); i > 0; i--) {
                System.out.print(' ');
            }
        }
        System.out.println();
    }
}

