package by.anthony.service;

import by.anthony.model.Intelligence;

import java.util.Scanner;

public interface GameSpeaker {

    int defineSize(Scanner scanner);

    Intelligence chooseOpponent(Scanner scanner);

    boolean isPlayAgain(Scanner scanner);

    static void checkInteger(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Sorry, you has entered not an integer");
            scanner.next();
        }
    }

}