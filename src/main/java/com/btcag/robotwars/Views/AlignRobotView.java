package com.btcag.robotwars.Views;

import com.btcag.robotwars.Enums.Direction;

import java.util.Scanner;

public class AlignRobotView {
    public static Direction display() {
        Scanner scanner = new Scanner(System.in);
        Direction direction = null;
        int index = 0;
        do {
            System.out.println(
                    "In which direction do you want to be aligned?\n" +
                            "Q  W  E\n" +
                            "A YOU D\n" +
                            "Y  S  X"
            );
            index = "WEDXSYAQ".indexOf((scanner.nextLine().toUpperCase() + " ").charAt(0));
            if (index >= 0) {
                direction = Direction.values()[index];
            }
        } while (direction == null);
        return direction;
    }
}