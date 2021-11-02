package by.anthony.model;

import java.util.Arrays;

public class Table {
    public static final char CELL_EMPTY = '\u0023';

    private final char[][] values;
    private final int size;

    public Table(int tableSize) {
        values = new char[tableSize][tableSize];
        this.size = tableSize;
        initTable();
    }

    public char[][] getValues() {
        return values;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        if (size != table.size) return false;
        return Arrays.deepEquals(values, table.values);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(values);
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                sb.append(values[row][column]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void initTable() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                values[row][column] = CELL_EMPTY;
            }
        }
    }

}