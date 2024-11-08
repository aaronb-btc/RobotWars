package com.btcag.robotwars;

public class Playfield {
    private int[] size;

    public Playfield(int width, int height) {
        this.size = new int[] {width, height};
    }

    String getPlayfield(Robot[] robots) {
        String seperator = "";
        for (long i = 0; i < this.size[0]; i++) {
            seperator += "|---";
        }
        seperator += '|';
        String playfield;
        playfield = seperator + "\n";
        char myChar;
        for (long y = 0; y < this.size[1]; y++) {
            for (long x = 0; x < this.size[0]; x++) {
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
        for (long x = 0; x < this.size[0]; x++) {
            playfield += x + 1;
            for (long i =  4 - ("" + x).length(); i > 0; i--) {
                playfield += ' ';
            }
        }
        return playfield;
    }

    int getWidth() {
        return this.size[0];
    }

    int getHeight() {
        return this.size[1];
    }
}
