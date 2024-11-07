package com.btcag.robotwars;

public  class Robot {
    private int x;
    private int y;
    private String name;
    private char displayChar;
    private int movementRate;

    public Robot(int x, int y, String name, char displayChar, int movementRate) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.displayChar = displayChar;
        this.movementRate = movementRate;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    public int getMovementRate() {
        return this.movementRate;
    }

    void moveRel(int xSteps, int ySteps) {
        this.x += xSteps;
        this.y += ySteps;
    }

    char getDisplayChar() {
        return this.displayChar;
    }

    String getName() {
        return this.name;
    }
}
