package com.btcag.robotwars.Services;

import java.util.Scanner;

public class Menu {
    private final String[] options;

    public Menu(String[] options) {
        this.options = options;
    }

    public String[] getOptions() {
        return this.options;
    }

    public int letUserChoose() {
        // stdin scanner to fetch user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number of an option: ");

        // display options
        for (int i = 1; i <= this.options.length; i++) {
            // Prints 1. First Option...
            System.out.print(i);
            System.out.print(". ");
            System.out.println(options[i - 1]);
        }

        // get user input
        int user_input;
        do {
            user_input = scanner.nextInt() - 1;
        } while (user_input < 0 || user_input >= options.length);

        return user_input;
    }
}
