package model;

public class BaseImage extends Sprite {
    public BaseImage(int size) {
        super(size);
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                table[row][col] = (col >= row) && (col < (size - row));
            }
        }
    }
}
