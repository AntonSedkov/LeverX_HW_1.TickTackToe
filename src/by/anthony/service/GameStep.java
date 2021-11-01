package by.anthony.service;

import by.anthony.model.Side;
import by.anthony.model.Table;

public interface GameStep {
    void step(Side side, Table table);

    void destroy();
}
