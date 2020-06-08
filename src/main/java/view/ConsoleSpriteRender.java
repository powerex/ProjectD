package view;

import model.Sprite;
import view.SpriteRender;

public class ConsoleSpriteRender implements SpriteRender {

    private char black = 'X';
    private char white = 32;

    @Override
    public void render(Sprite image) {
        for (int row = 0; row < image.getSize(); ++row) {
            for (int col = 0; col < image.getSize(); ++col) {
                System.out.print((image.getPixel(row, col))?black:white);
            }
            System.out.println();
        }
        System.out.println();
    }
}
