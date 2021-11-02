package by.anthony.model;

import java.util.StringJoiner;

public class Player {
    private final Side side;
    private final Intelligence intelligence;

    public Player(Side side, Intelligence intelligence) {
        this.side = side;
        this.intelligence = intelligence;
    }

    public Side getSide() {
        return side;
    }

    public Intelligence getIntelligence() {
        return intelligence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        if (side != player.side) return false;
        return intelligence == player.intelligence;
    }

    @Override
    public int hashCode() {
        int result = side != null ? side.hashCode() : 0;
        result = 31 * result + (intelligence != null ? intelligence.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Player.class.getSimpleName() + "[", "]")
                .add("side=" + side)
                .add("intelligence=" + intelligence)
                .toString();
    }

}