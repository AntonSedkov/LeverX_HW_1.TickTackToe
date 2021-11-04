package by.anthony.service;

import by.anthony.model.Intelligence;
import by.anthony.model.Player;
import by.anthony.model.Side;
import by.anthony.model.Table;
import by.anthony.service.impl.BotStepImpl;
import by.anthony.service.impl.GameSpeakerImpl;
import by.anthony.service.impl.HumanStepImpl;
import by.anthony.service.impl.WinCheckerImpl;

import java.util.Scanner;

public class GameRuler {

    public void play() {
        try (Scanner scanner = new Scanner(System.in)) {
            GameSpeaker gameSpeaker = new GameSpeakerImpl();
            do {
                int size = gameSpeaker.defineSize(scanner);
                Table table = new Table(size);
                System.out.println(table.toString());

                Intelligence intelligence = gameSpeaker.chooseOpponent(scanner);
                Player secondGamer = new Player(Side.PLAYER_O, intelligence);

                action(secondGamer, table, scanner);

            } while (gameSpeaker.isPlayAgain(scanner));
        }
    }

    private void action(Player secondGamer, Table table, Scanner scanner) {
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
                int stepsForDraw = size / 2 + 1;
                gameOver = (step++ > stepsForDraw) &&
                        winChecker.checkDraw(table);
                if (gameOver) {
                    System.out.println("This is draw!!!");
                    break;
                }
            }
        }
    }

}