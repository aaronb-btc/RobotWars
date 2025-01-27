package com.btcag.robotwars.Enums;

public enum Direction {
    NORTH       ( 0, -1),
    NORTHEAST   ( 1, -1),
    EAST        ( 1,  0),
    SOUTHEAST   ( 1,  1),
    SOUTH       ( 0,  1),
    SOUTHWEST   (-1,  1),
    WEST        (-1,  0),
    NORTHWEST   (-1, -1);

    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
