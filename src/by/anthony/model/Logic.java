package by.anthony.model;

import java.util.Random;
import java.util.Scanner;

public class Logic {

    private static final int CELL_EMPTY = 0;
    private final int[][] table;
    private final int winX;
    private final int winO;

    private Random random;
    private Scanner scanner;

    public Logic(int tableSize) {
        this.table = new int[tableSize][tableSize];
        this.winX = Side.CELL_X.getValue() * tableSize;
        this.winO = Side.CELL_O.getValue() * tableSize;

        random = new Random();
        scanner = new Scanner(System.in);
    }

    public void start() {
        initTable();

        while (true) {
           /* step();
            checkWin();
            step();
            checWin();
            aWin();
            aEnd();
            aBreak();*/
        }
    }

    private void initTable() {
        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                table[row][column] = CELL_EMPTY;
            }
        }
    }

    private void currentTable() {
        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                System.out.print(table[row][column]);
            }
            System.out.println();
        }
    }

    private void stepX() {
        int x;
        int y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = Side.CELL_X.getValue();
    }

    private void stepO() {
        int x;
        int y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = Side.CELL_O.getValue();
    }

    private void stepAI(int size, Side side) {
        int x;
        int y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (!isCellValid(x, y));
        table[y][x] = side.getValue();
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3 || y >= 3) {
            return false;
        }
        return table[y][x] == CELL_EMPTY;
    }

    private boolean checkWin() {
        boolean victory = false;
        int index = table.length - 1;
        while (index >= 0) {

            boolean isColWin = checkColumn(index, table[index].length - 1);
            if (isColWin) {
                victory = isColWin;
                break;
            }

            boolean isRowWin = checkRow(table.length - 1, index);
            if (isRowWin) {
                victory = isRowWin;
                break;
            }
            index--;
        }
        if (!victory) {
            victory = (checkMainDiagonal() || checkMinorDiagonal());
        }
        return victory;
    }

    private boolean checkColumn(int row, int column) {
        boolean isColWin = false;
        if (table[row][column] == CELL_EMPTY) {
            return isColWin;
        }
        while (column > 0) {
            if (table[row][column] != table[row][--column]) {
                isColWin = false;
                break;
            }
            isColWin = true;
        }
        return isColWin;
    }

    private boolean checkRow(int row, int column) {
        boolean isRowWin = false;
        if (table[row][column] == CELL_EMPTY) {
            return isRowWin;
        }
        while (row > 0) {
            if (table[row][column] != table[--row][column]) {
                isRowWin = false;
                break;
            }
            isRowWin = true;
        }
        return isRowWin;
    }

    private boolean checkMainDiagonal() {
        boolean result = false;
        int index = 0;
        while (index < table.length - 1) {
            if (table[index][index] == CELL_EMPTY) {
                result = false;
                break;
            }
            if (table[index][index] != table[++index][index]) {
                result = false;
                break;
            }
            result = true;
        }
        return result;
    }

    private boolean checkMinorDiagonal() {
        boolean result = false;
        int index = 0;
        while (index < table.length - 1) {
            if (table[index][table.length - 1 - index] == CELL_EMPTY) {
                result = false;
                break;
            }
            if (table[index][table.length - 1 - index] != table[++index][table.length - 1 - index]) {
                result = false;
                break;
            }
            result = true;
        }
        return result;
    }

}
