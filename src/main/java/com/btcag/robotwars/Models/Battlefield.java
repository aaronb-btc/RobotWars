package com.btcag.robotwars.Models;

public class Battlefield {
    private int width;
    private int height;

    public Battlefield(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isValidField(int x, int y) {
        return x >= 0 && y >= 0 && x < this.width && y < this.height;
    }
}
