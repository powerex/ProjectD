package model;

public class BaseImageCW  extends Sprite {
    public BaseImageCW(int size) {
        super(size);
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                table[row][col] = (col >= row) && (col + row >= size-1);
            }
        }
    }
}
