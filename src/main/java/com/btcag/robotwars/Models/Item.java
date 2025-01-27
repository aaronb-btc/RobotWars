package com.btcag.robotwars.Models;

import com.btcag.robotwars.Enums.ItemType;

public class Item {
    // I'D RATHER CODE IN C++
    private final ItemType itemType;
    private final int intensity;
    private int duration;
    private final int x; // Is only useful on maps
    private final int y; // Is only useful on maps

    public Item(ItemType itemType, int intensity, int duration, int x, int y) {
        this.itemType = itemType;
        this.intensity = intensity;
        this.duration = duration;
        this.x = x;
        this.y = y;
    }

    public int getDuration() {
        return duration;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getIntensity() {
        return intensity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void decreaseDuration() {
        this.duration--;
    }
}