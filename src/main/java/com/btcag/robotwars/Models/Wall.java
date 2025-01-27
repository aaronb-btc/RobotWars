package com.btcag.robotwars.Models;

public class Wall {
    private final int x;
    private final int y;
    private boolean onBattlefield = true;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isONBattlefield() {
        return this.onBattlefield;
    }

    public void destroyWall() {
        this.onBattlefield = false;
    }
}
