package com.btcag.robotwars.Enums;

public enum ItemType {
    MOVEMENT_RATE(0.5, 0.25),
    ATTACK_RANGE(.5, 0.5),
    ATTACK_DAMAGE(0.5, 0.75);

    public final double x;
    public final double y;

    ItemType(double x, double y) {
        this.x = x;
        this.y = y;
    }
}