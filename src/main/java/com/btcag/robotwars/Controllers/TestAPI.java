package com.btcag.robotwars.Controllers;

import com.btcag.robotwars.Api.model.NewRobot;
import com.btcag.robotwars.Models.Map;
import com.btcag.robotwars.Services.APIFunctions;
import com.btcag.robotwars.Services.Menu;
import com.btcag.robotwars.Api.ApiException;
import com.btcag.robotwars.Views.BattlefieldView;

import java.util.Arrays;
import java.util.Scanner;

public class TestAPI {
    public static void main(String[] args) throws ApiException {
        Menu mainMenu = new Menu(new String[] {"Get Bots", "Get Bot using id", "Create Bot", "Get Maps", "Quit"});
        Scanner scanner = new Scanner(System.in);
        int chosenOption;

        do {
            chosenOption = mainMenu.letUserChoose();

            switch (chosenOption) {
                case 0 -> {
                    System.out.println(Arrays.toString(APIFunctions.getRobots()));
                }
                case 1 -> {
                    System.out.println("Enter the UUID:");
                    String id = scanner.next();
                    System.out.println(APIFunctions.getRobot(id));
                }
                case 2 -> {
                    NewRobot robot = GameController.createPlayerRobot(15);
                    System.out.println(APIFunctions.createRobot(robot).getId());
                }
                case 3 -> {
                    // Request and store maps from API
                    java.util.Map<String, Object>[] apiMaps = APIFunctions.getMaps();

                    // Convert and store maps to own more usable model
                    com.btcag.robotwars.Models.Map[] maps = new Map[apiMaps.length];
                    for (int i = 0; i < maps.length; i++) {
                        System.out.println();
                        System.out.println(i + 1);
                        BattlefieldView.display(apiMaps[i]);
                        // maps[i] = new com.btcag.robotwars.Models.Map(apiMaps[i]);
                    }

                    /* print out Maps and their numbers
                    for (int i = 0; i < maps.length; i++) {
                        System.out.println();
                        System.out.println(i + 1);
                        BattlefieldView.display(maps[i]);
                    }
                     */
                }
            }
        } while (!mainMenu.getOptions()[chosenOption].equalsIgnoreCase("quit"));
    }
}