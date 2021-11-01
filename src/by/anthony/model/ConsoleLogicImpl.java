package by.anthony.model;

import java.util.Scanner;

public class ConsoleLogicImpl implements Logic {
    private static final char CELL_EMPTY = '\u0023';
    private final char[][] table;
    private Scanner scanner;

    public ConsoleLogicImpl(int tableSize) {
        this.table = new char[tableSize][tableSize];
        scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        initTable();
        currentTable();

        boolean gameOver = false;
        int step = 1;
        while (!gameOver) {
            for (Side side : Side.values()) {
                step(side);
                currentTable();
                gameOver = (step++ > table.length) && checkWin();
                if (gameOver) {
                    System.out.println(side.name() + " is Win. Congratulations!!!");
                    break;
                }
                gameOver = isTableFull();
                if (gameOver) {
                    System.out.println("This is draw!!!");
                    break;
                }
            }
        }
    }

    @Override
    public void destroy() {
        scanner.close();
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
                System.out.print(table[row][column] + "\t");
            }
            System.out.println();
        }
    }

    private void step(Side side) {
        int x = 0;
        int y = 0;
        boolean isStepDone = false;
        while (!isStepDone) {
            System.out.println("Please, choose X and Y for your step (for example: 1 3)");
            skipBeforeInt();
            x = scanner.nextInt() - 1;
            skipBeforeInt();
            y = scanner.nextInt() - 1;
            if (!isCellValid(x, y)) {
                System.out.println("This cell is inaccessible. Choose other cell.");
            } else {
                isStepDone = true;
            }
        }
        table[y][x] = side.getValue();
    }

    private void skipBeforeInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Sorry, you has entered not an integer");
            scanner.next();
        }
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= table.length || y >= table.length) {
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

    //todo
    private boolean isTableFull() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table.length; col++) {
                if (table[row][col] == CELL_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


}