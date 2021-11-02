package by.anthony.service.impl;

import by.anthony.model.Side;
import by.anthony.model.Table;
import by.anthony.service.GameStep;

import java.util.Scanner;

public class BotStepImpl implements GameStep {

    @Override
    public void step(Side side, Table table, Scanner scanner) {
        boolean stepDone = false;
        while (!stepDone) {
            int x = (int) (Math.random() * table.getSize());
            int y = (int) (Math.random() * table.getSize());
            if (table.getValues()[x][y] == Table.CELL_EMPTY) {
                table.getValues()[x][y] = side.getValue();
                stepDone = true;
            }
        }
    }

}