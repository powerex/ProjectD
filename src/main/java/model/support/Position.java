package model.support;

public class Position {

    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Position getPositionFromFlatIndex(int flatIndex, int cols) {
        return new Position(flatIndex / cols, flatIndex % cols);
    }

    public int getFlatPosition(int size) {
        return row * size + col;
    }

}
