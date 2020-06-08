package model;

import model.support.Position;

public abstract class Sprite {

    protected boolean[][] table;
    protected int size;

    public Sprite(int size) {
        this.size = size;
        table = new boolean[size][size];
    }

    public Sprite(boolean[][] table) {
        this.table = table;
        size = table.length;
    }

    public int getSize() {
        return size;
    }

    public boolean[][] getTable() {
        return table;
    }

    public boolean getPixel(int row, int col) {
        return table[row][col];
    }

    public boolean getPixel(Position pos) {
        return table[pos.row][pos.col];
    }

}
