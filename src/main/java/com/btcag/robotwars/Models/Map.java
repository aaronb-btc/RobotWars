package com.btcag.robotwars.Models;

import com.btcag.robotwars.Api.model.MapMapItems;
import com.btcag.robotwars.Enums.MapItemType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private final String id;
    private final int mapSizeX;
    private final int mapSize;
    private char[] charMap;
    private List<MapItem> mapItems;

    public Map(String id, int mapSizeX, int mapSize, List<MapItem> mapItems) {
        this.id = id;
        this.mapSizeX = mapSizeX;
        this.mapSize = mapSize;
        this.mapItems = mapItems;
        updateCharMap();
    }

    public Map(java.util.Map<String, Object> map) {
        // Create an empty List
        List<MapItem> mapItems = new java.util.ArrayList<>(List.of(new MapItem[0]));

        // Convert existing MapItems to own model and store them iin the List
        for (java.util.Map<String, Object> mapItem : (ArrayList<java.util.Map>) map.get("mapItems")) {
            mapItems.add(new MapItem(
                    MapItemType.valueOf((String) mapItem.get("type")),
                    ((Double) mapItem.get("index")).intValue())
            );
        }
        this(
                (String) map.get("id"),
                ((Double) map.get("mapSizeX")).intValue(),
                ((Double) map.get("mapSize")).intValue(),
                mapItems
        );
    }

    public String getId() {
        return id;
    }

    public int getMapSizeX() {
        return mapSizeX;
    }

    public int getMapSizeY() {
        return charMap.length / mapSizeX;
    }

    public int getMapSize() {
        return charMap.length;
    }

    public char[] getCharMap() {
        return charMap;
    }

    public List<MapItem> getMapItems() {
        return mapItems;
    }

    public void updateCharMap() {
        charMap = new char[mapSize];
        Arrays.fill(charMap, ' ');
        for (MapItem mapItem : mapItems) {
            charMap[mapItem.getMapIndex()] = mapItem.getMapItemType().mapChar;
        }
    }

    public void addMapItem(MapItem mapItem) {
        mapItems.add(mapItem);
    }

    public void removeMapItem(int index) {
        mapItems.remove(index);
    }

    public boolean isValidField(int x, int y) {
        for (MapItem mapItem : mapItems) {
            if (
                    mapItem.getMapItemType() == MapItemType.WALL
                    && mapItem.getMapIndex() == mapSizeX * y + x
            ) {
                return false;
            }
        }

        return x < mapSizeX && x >= 0 && y < getMapSizeY() && y >= 0; // false if (x, y)  outside (mapSizeX, getMapSizeY())
    }

    @Override
    public String toString() {
        return "Map{" +
                "id='" + id + '\'' +
                ", mapSizeX=" + mapSizeX +
                ", mapSize=" + mapSize +
                ", charMap=" + Arrays.toString(charMap) +
                ", mapItems=" + mapItems +
                '}';
    }
}
