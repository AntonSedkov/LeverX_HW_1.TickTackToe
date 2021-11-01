package by.anthony.model;

public class GameRuler {

    public void play() {
        GameSpeaker speaker = new GameSpeaker();
        int size = speaker.defineSize();

        Logic logic = new ConsoleLogicImpl(size);
        logic.start();
        logic.destroy();
    }
}
