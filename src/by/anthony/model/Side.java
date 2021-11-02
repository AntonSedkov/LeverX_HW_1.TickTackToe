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

    @Override
    public String toString() {
        String name = this.name();
        char delimiter = '_';
        StringBuilder sb = new StringBuilder()
                .append(name, 0, 1)
                .append(name.substring(1, name.indexOf(delimiter)).toLowerCase())
                .append(name.substring(name.indexOf(delimiter)).replace("_", " "));
        return sb.toString();
    }

}