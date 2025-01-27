package com.btcag.robotwars.Models;

import com.btcag.robotwars.Enums.MapItemType;

public class MapItem {
    private final MapItemType mapItemType;
    private final int mapIndex;

    public MapItem(MapItemType mapItemType, int mapIndex) {
        this.mapItemType = mapItemType;
        this.mapIndex = mapIndex;
    }

    public MapItem(com.btcag.robotwars.Api.model.MapMapItems mapItem) {
        this(
                MapItemType.valueOf(mapItem.getType().toString()),
                mapItem.getIndex().intValue()
        );
    }

    public MapItemType getMapItemType() {
        return this.mapItemType;
    }

    public int getMapIndex() {
        return this.mapIndex;
    }

    @Override
    public String toString() {
        return "MapItem{" +
                "  mapItemType=" + mapItemType +
                ", mapIndex=" + mapIndex +
                '}';
    }
}
