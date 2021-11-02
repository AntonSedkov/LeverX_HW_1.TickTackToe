package by.anthony.service;

import by.anthony.model.Side;
import by.anthony.model.Table;

import java.util.Scanner;

public interface GameStep {

    void step(Side side, Table table, Scanner scanner);

}