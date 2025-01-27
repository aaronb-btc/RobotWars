package com.btcag.robotwars.Controllers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void random() {
        int[] numbers = new int[1000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = GameController.random(3, 5);
            assertTrue(numbers[i] >= 3 && numbers[i] <= 5, "random returns value outside given range\nRange was 3 to 5\nReturned value is " + numbers[i]);
        }
        for (int number = 3; number <= 5; number++) {
            assertTrue(Arrays.asList(number).contains(number), "random with range of 3 to 5 does not return "+ number + " in " + numbers.length + " passes.");
        }
    }
}