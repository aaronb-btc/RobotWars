package com.btcag.robotwars.Services;

public class PosMod {
    public static long posMod(long a, long b) {
        long result = a % b;
        return result + (result < 0 ? b : 0);
    }
}
