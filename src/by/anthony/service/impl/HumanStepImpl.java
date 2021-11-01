package by.anthony.service.impl;

import by.anthony.model.Side;
import by.anthony.model.Table;
import by.anthony.service.GameStep;

import java.util.Scanner;

public class HumanStepImpl implements GameStep {
    private final Scanner scanner;

    public HumanStepImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void step(Side side, Table table) {
        int x = 0;
        int y = 0;
        boolean isStepDone = false;
        while (!isStepDone) {
            System.out.println("Please, choose X and Y for your step (for example: 1 3)");
            skipBeforeInt();
            x = scanner.nextInt() - 1;
            skipBeforeInt();
            y = scanner.nextInt() - 1;
            if (!isCellValid(table.getValues(), table.getSize(), x, y)) {
                System.out.println("This cell is inaccessible. Choose other cell.");
            } else {
                isStepDone = true;
            }
        }
        table.getValues()[y][x] = side.getValue();
    }

    @Override
    public void destroy() {
        scanner.close();
    }

    private void skipBeforeInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Sorry, you has entered not an integer");
            scanner.next();
        }
    }

    private boolean isCellValid(char[][] table, int tableSize, int x, int y) {
        if (x < 0 || y < 0 || x >= tableSize || y >= tableSize) {
            return false;
        }
        return table[y][x] == Side.CELL_EMPTY.getValue();
    }

}