package model;

import model.support.Position;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprite sprite = (Sprite) o;
        return size == sprite.size &&
                Arrays.equals(table, sprite.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }
}
