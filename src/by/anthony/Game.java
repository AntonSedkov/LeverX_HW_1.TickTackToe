package by.anthony;

import by.anthony.service.GameRuler;

public class Game {

    public static void main(String[] args) {
        GameRuler game = new GameRuler();
        game.play();
        game.destroy();
    }
}
