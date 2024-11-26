package com.btcag.robotwars.Views;

import com.btcag.robotwars.Models.Robot;

import java.util.Scanner;

public class AskRobotNameView {
    public static String display() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username:");
        String name = scanner.nextLine();
        System.out.println("\nWelcome, " + name + '!');

        return name;
    }
}