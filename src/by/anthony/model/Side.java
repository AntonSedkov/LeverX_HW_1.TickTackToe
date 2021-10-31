package by.anthony.model;

public enum Side {
    CELL_X(100),
    CELL_O(10);

    private final int value;

    Side(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
