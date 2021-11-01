package by.anthony.service;

import by.anthony.model.Table;

public interface WinChecker {
    boolean checkWin(Table gameTable);

    boolean checkDraw(Table gameTable);
}