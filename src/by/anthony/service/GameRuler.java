package by.anthony.service;

import by.anthony.model.Intelligence;
import by.anthony.model.Player;
import by.anthony.model.Side;
import by.anthony.model.Table;
import by.anthony.service.impl.BotStepImpl;
import by.anthony.service.impl.HumanStepImpl;
import by.anthony.service.impl.WinCheckerImpl;

import java.util.Scanner;

public class GameRuler {
    private final Scanner scanner;

    public GameRuler() {
        scanner = new Scanner(System.in);
    }

    public void play() {
        try {
            do {
                int size = defineSize();
                Table table = new Table(size);
                System.out.println(table.toString());

                Intelligence intelligence = chooseOpponent();
                Player secondGamer = new Player(Side.PLAYER_O, intelligence);

                action(secondGamer, table);

            } while (playAgain());
        } finally {
            destroy();
        }
    }

    private void action(Player secondGamer, Table table) {
        WinChecker winChecker = new WinCheckerImpl();
        int size = table.getSize();
        boolean gameOver = false;
        int step = 1;

        while (!gameOver) {
            for (Side side : Side.values()) {
                GameStep gameStep = new HumanStepImpl();
                if (side == secondGamer.getSide()) {
                    gameStep = (secondGamer.getIntelligence() == Intelligence.BOT) ? new BotStepImpl() : gameStep;
                }
                gameStep.step(side, table, scanner);
                System.out.println(table.toString());
                gameOver = (step++ > size) &&
                        winChecker.checkWin(table);
                if (gameOver) {
                    System.out.println(side.toString() + " is Win. Congratulations!!!");
                    break;
                }
                gameOver = (step++ > (size / 2 + 1)) &&
                        winChecker.checkDraw(table);
                if (gameOver) {
                    System.out.println("This is draw!!!");
                    break;
                }
            }
        }
    }

    private int defineSize() {
        int size;
        System.out.println("Enter the size of table");
        checkInteger();
        size = scanner.nextInt();
        return size;
    }

    private void checkInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println("Sorry, you has entered not an integer");
            scanner.next();
        }
    }

    private Intelligence chooseOpponent() {
        boolean incorrect = true;
        Intelligence intelligence = Intelligence.HUMAN;
        while (incorrect) {
            System.out.println("Enter the opponent's number:\n 1 - Human;\n 2 - Bot");
            checkInteger();
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

    private boolean playAgain() {
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

    private void destroy() {
        scanner.close();
    }

}