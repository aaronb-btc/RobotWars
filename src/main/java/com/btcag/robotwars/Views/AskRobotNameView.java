package com.btcag.robotwars.Views;

import java.util.Scanner;

public class AskRobotNameView {
    public static String display() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name your robot:");
        String name = scanner.nextLine();

        return name;
    }
}