package com.btcag.robotwars.Views;

import java.util.Map;

public class DisplayMaps {
    public static void display(Map<String, Object>[] maps) {
        int counter = 0;
        for (Map<String, Object> map : maps) {
            if (counter > 0) {
                System.out.println();
            }

            System.out.print(++counter);
            System.out.println(':');

            BattlefieldView.display(map);
        }
    }
}
