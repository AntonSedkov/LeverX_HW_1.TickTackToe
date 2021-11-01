package by.anthony.service.impl;

import by.anthony.model.Side;
import by.anthony.model.Table;
import by.anthony.service.WinChecker;

public class WinCheckerImpl implements WinChecker {

    public WinCheckerImpl() {
    }

    @Override
    public boolean checkWin(Table gameTable) {
        char[][] table = gameTable.getValues();
        int tableSize = gameTable.getSize();
        boolean victory = false;
        int index = tableSize - 1;
        while (index >= 0) {
            boolean isColWin = checkWinColumn(table, index, tableSize - 1);
            if (isColWin) {
                victory = isColWin;
                break;
            }
            boolean isRowWin = checkWinRow(table, tableSize - 1, index);
            if (isRowWin) {
                victory = isRowWin;
                break;
            }
            index--;
        }
        if (!victory) {
            victory = (checkWinMainDiagonal(table, tableSize) ||
                    checkWinMinorDiagonal(table, tableSize));
        }
        return victory;
    }

    @Override
    public boolean checkDraw(Table gameTable) {
        char[][] table = gameTable.getValues();
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table.length; col++) {
                if (table[row][col] == Side.CELL_EMPTY.getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWinColumn(char[][] table, int row, int column) {
        boolean isColWin = false;
        if (table[row][column] == Side.CELL_EMPTY.getValue()) {
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

    private boolean checkWinRow(char[][] table, int row, int column) {
        boolean isRowWin = false;
        if (table[row][column] == Side.CELL_EMPTY.getValue()) {
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

    private boolean checkWinMainDiagonal(char[][] table, int tableSize) {
        boolean result = false;
        int index = 0;
        while (index < tableSize - 1) {
            if (table[index][index] == Side.CELL_EMPTY.getValue()) {
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

    private boolean checkWinMinorDiagonal(char[][] table, int tableSize) {
        boolean result = false;
        int index = 0;
        while (index < tableSize - 1) {
            if (table[index][tableSize - 1 - index] == Side.CELL_EMPTY.getValue()) {
                result = false;
                break;
            }
            if (table[index][tableSize - 1 - index] != table[++index][tableSize - 1 - index]) {
                result = false;
                break;
            }
            result = true;
        }
        return result;
    }

}