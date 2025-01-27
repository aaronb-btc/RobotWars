package com.btcag.robotwars.Enums;

public enum MapItemType {
    ROBOT('R'),
    WALL('#'),
    ITEM('*');

    public final char mapChar;

    MapItemType(char mapChar) {
        this.mapChar = mapChar;
    }
}
