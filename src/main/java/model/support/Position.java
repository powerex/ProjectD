package model.support;

public class Position {

    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getFlatPosition(int size) {
        return row * size + col;
    }

}
