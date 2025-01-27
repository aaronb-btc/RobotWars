package com.btcag.robotwars.Views;

import com.btcag.robotwars.Enums.MapItemType;
import com.btcag.robotwars.Models.*;

import java.util.ArrayList;
import java.util.Arrays;

public class BattlefieldView {
    public static void display(Map battlefield) {
        char[] map = battlefield.getCharMap();

        StringBuilder seperator = new StringBuilder();
        for (long i = 0; i < battlefield.getMapSizeX(); i++) {
            seperator.append("|---");
        }
        seperator.append('|');
        System.out.println(seperator);
        for (int y = 0; y < battlefield.getMapSizeY(); y++) {
            for (int x = 0; x < battlefield.getMapSizeX(); x++) {
                System.out.print("| " + map[battlefield.getMapSizeX() * y + x] + ' ');
            }
            System.out.println("| " + (y + 1) + "\n" + seperator);
        }
        System.out.print(' ');

        // This adds the column numbers at the bottom
        for (int x = 1; x <= battlefield.getMapSizeX(); x++) {
            System.out.print(x);
            for (long i =  4 - ((int) (Math.log10(x) + 1)); i > 0; i--) {
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    public static void display(java.util.Map<String, Object> map) {
        int[] indexes = new int[((ArrayList) map.get("mapItems")).size()];
        char[] chars = new char[indexes.length];

        for (int i = 0; i < indexes.length; i++) {
            java.util.Map<String, Object> mapItem = ((ArrayList<java.util.Map>) map.get("mapItems")).get(i);
            indexes[i] = ((Double) mapItem.get("index")).intValue();
            chars[i] = ((String) mapItem.get("type")).charAt(0);
        }

        char[] charMap = new char[((Double) map.get("mapSize")).intValue()];
        Arrays.fill(charMap, ' ');

        for (int i = 0; i < indexes.length; i++) {
            charMap[indexes[i]] = chars[i];
        }

        int mapWidth = ((Double) map.get("mapSizeX")).intValue();
        indexes = null;
        chars = null;
        map = null;

        System.out.print('┌');
        System.out.print("───┬".repeat(mapWidth).substring(0, mapWidth * 4 - 1));
        System.out.println('┐');
        for (int i = 0; i < charMap.length; i++) {
            if (i % mapWidth == 0 && i != 0) {
                System.out.print("│ ");
                System.out.println(i / charMap.length);
                System.out.print('├');
                System.out.print("┼───".repeat(mapWidth).substring(1, mapWidth * 4));
                System.out.println('┤');
            }

            System.out.print("│ ");
            System.out.print(charMap[i]);
            System.out.print(' ');
        }
        System.out.print("│ ");
        System.out.println(charMap.length / mapWidth);
        System.out.print('└');
        System.out.print("───┴".repeat(mapWidth).substring(0, mapWidth * 4 - 1));
        System.out.println('┘');

        int i;
        int spacing;
        for (int j = 1; j <= mapWidth; j++) {
            i = j + 5;
            spacing = 4 - (int) (Math.log10(i) + 1);
            if (spacing < 0) spacing = 0;
            else System.out.print(" ".repeat((int) Math.ceil((double) spacing / 2)));
            System.out.print(i);
            if (spacing != 0) System.out.print(" ".repeat(spacing / 2));
        }
        System.out.println();
    }
}