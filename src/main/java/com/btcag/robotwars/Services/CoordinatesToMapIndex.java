package com.btcag.robotwars.Services;

public class CoordinatesToMapIndex {
    public static int coords2inde(int x, int y, int mapSizeX) {
        return x + y * mapSizeX;
    }
}
