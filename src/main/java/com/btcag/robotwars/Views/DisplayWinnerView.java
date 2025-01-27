package com.btcag.robotwars.Views;

import com.btcag.robotwars.Models.Robot;

public class DisplayWinnerView {
    public static void display(Robot winner) {
        System.out.println("Winner: " + winner.getName());
    }
}
