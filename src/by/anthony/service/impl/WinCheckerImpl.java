package by.anthony.service.impl;

import by.anthony.model.Table;
import by.anthony.service.WinChecker;

public class WinCheckerImpl implements WinChecker {

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
                if (table[row][col] == Table.CELL_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWinColumn(char[][] table, int row, int column) {
        boolean isColWin = false;
        if (table[row][column] == Table.CELL_EMPTY) {
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
        if (table[row][column] == Table.CELL_EMPTY) {
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
            if (table[index][index] == Table.CELL_EMPTY) {
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
            if (table[index][tableSize - 1 - index] == Table.CELL_EMPTY) {
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