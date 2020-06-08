package model;

public class BaseImageCCW  extends Sprite {
    public BaseImageCCW(int size) {
        super(size);
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                table[row][col] = (col <= row) && (col >= (size - row - 1));
            }
        }
    }
}
