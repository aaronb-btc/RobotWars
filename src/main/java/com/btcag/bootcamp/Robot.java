package com.btcag.bootcamp;

public  class Robot {
    private int x;
    private int y;
    private String name;
    private char displayChar;

    public Robot(int x, int y, String name, char displayChar) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.displayChar = displayChar;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
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
