package model;

import model.*;
import model.support.UnsupportedTypeException;

public class StandardFactory {
    public static Sprite getSprite(int size, FigureType type) throws UnsupportedTypeException {
        switch (type) {
            case UP:
                return new BaseImage(size);
            case LEFT:
                return new BaseImageR(size);
            case RIGHT:
                return new BaseImageCW(size);
            case DOWN:
                return new BaseImageCCW(size);
            default:
                throw new UnsupportedTypeException();
        }
    }
}
