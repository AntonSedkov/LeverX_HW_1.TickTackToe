package by.anthony.model;

public enum Side {
    PLAYER_X('X'),
    PLAYER_O('O');

    private final char value;

    Side(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
