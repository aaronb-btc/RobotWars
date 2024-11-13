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
        String playfield;
        playfield = seperator + "\n";
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
                playfield += "| " + myChar + ' ';
            }
            playfield += "| " + (y + 1) + "\n" + seperator + "\n";
        }
        playfield += ' ';

        // This adds the column numbers at the bottom
        for (long x = 0; x < battlefield.getWidth(); x++) {
            playfield += x + 1;
            for (long i =  4 - ("" + x).length(); i > 0; i--) {
                playfield += ' ';
            }
        }
        return playfield;
    }

    private long getWidth() {
    }
}
}
