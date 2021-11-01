package by.anthony.service;

import by.anthony.model.Opponent;
import by.anthony.model.Side;
import by.anthony.model.Table;
import by.anthony.service.impl.HumanStepImpl;
import by.anthony.service.impl.WinCheckerImpl;

import java.util.Scanner;

public class GameRuler {
    private final Scanner scanner;

    public GameRuler() {
        scanner = new Scanner(System.in);
    }

    public void play() {
        int size = defineSize();

        Table table = new Table(size);
        System.out.println(table.toString());

        WinChecker winChecker = new WinCheckerImpl();
        GameStep gameStep = new HumanStepImpl();

        //todo insert Opponent

        boolean gameOver = false;
        int step = 1;

        while (!gameOver) {
            Side[] players = new Side[]{Side.PLAYER_X, Side.PLAYER_O};
            for (Side side : players) {
                gameStep.step(side, table);
                System.out.println(table.toString());
                gameOver = (step++ > size) &&
                        winChecker.checkWin(table);

                if (gameOver) {
                    System.out.println(side.toString() + " is Win. Congratulations!!!");
                    gameStep.destroy();
                    break;
                }
                gameOver = (step++ > (size / 2 + 1)) &&
                        winChecker.checkDraw(table);
                if (gameOver) {
                    System.out.println("This is draw!!!");
                    gameStep.destroy();
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

    private Opponent chooseOpponent() {
        int opponent;
        System.out.println("Enter the opponent's number:\n 1 - Human;\n 2 - Bot");
        checkInteger();
        opponent = scanner.nextInt();
        switch (opponent) {
            case 1 -> {
                return Opponent.HUMAN;
            }
            case 2 -> {
                return Opponent.BOT;
            }
            default -> {
                System.out.println("Wrong opponent's number");
                return chooseOpponent();
            }
        }
    }

    //todo
    private void oneMoreGame() {

    }

    private void checkInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println("Sorry, you has entered not an integer");
            scanner.next();
        }
    }

    public void destroy() {
        scanner.close();
    }
}
