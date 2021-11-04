package by.anthony.service.impl;

import by.anthony.model.Intelligence;
import by.anthony.service.GameSpeaker;

import java.util.Scanner;

public class GameSpeakerImpl implements GameSpeaker {

    @Override
    public int defineSize(Scanner scanner) {
        int size = 3;
        System.out.println("Enter the size of table (greater than 1)");
        boolean incorrect = true;
        while (incorrect) {
            GameSpeaker.checkInteger(scanner);
            size = scanner.nextInt();
            if (size < 2) {
                System.out.println("Sorry, you has entered incorrect integer");
            } else {
                incorrect = false;
            }
        }
        return size;
    }

    @Override
    public Intelligence chooseOpponent(Scanner scanner) {
        boolean incorrect = true;
        Intelligence intelligence = Intelligence.HUMAN;
        while (incorrect) {
            System.out.println("Enter the opponent's number:\n 1 - Human;\n 2 - Bot");
            GameSpeaker.checkInteger(scanner);
            int opponent = scanner.nextInt();
            switch (opponent) {
                case 1 -> incorrect = false;
                case 2 -> {
                    intelligence = Intelligence.BOT;
                    incorrect = false;
                }
                default -> System.out.println("Wrong opponent's number");
            }
        }
        return intelligence;
    }

    @Override
    public boolean isPlayAgain(Scanner scanner) {
        boolean incorrect = true;
        boolean nextGame = false;
        while (incorrect) {
            System.out.println("Do you wish start new game? (write Y/N)");
            char isAgain = scanner.next().charAt(0);
            switch (isAgain) {
                case 'Y', 'y' -> {
                    nextGame = true;
                    incorrect = false;
                }
                case 'N', 'n' -> incorrect = false;
                default -> System.out.println("Wrong answer, please, write Y or N");
            }
        }
        return nextGame;
    }

}