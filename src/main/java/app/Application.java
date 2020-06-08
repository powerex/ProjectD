package app;

import model.FigureType;
import model.MutationFactory;
import model.Sprite;
import model.StandardFactory;
import model.support.Position;
import model.support.UnsupportedTypeException;
import view.ConsoleSpriteRender;
import view.SpriteRender;

public class Application {

    private static final int SIZE = 7;

    public static void main(String[] args) {
        Sprite[] sprites = new Sprite[4];
        try {
            sprites[0] = StandardFactory.getSprite(SIZE, FigureType.UP);
            sprites[1] = StandardFactory.getSprite(SIZE, FigureType.LEFT);
            sprites[2] = StandardFactory.getSprite(SIZE, FigureType.DOWN);
            sprites[3] = StandardFactory.getSprite(SIZE, FigureType.RIGHT);
        } catch (UnsupportedTypeException e) {
            e.printStackTrace();
        }

        SpriteRender render = new ConsoleSpriteRender();
        for (Sprite s : sprites)
            render.render(s);

        System.out.println("====================================");

        Sprite[] figures01 = new Sprite[SIZE * SIZE];
        for (int row =0; row < SIZE; ++row) {
            for (int col =0; col<SIZE; ++col) {
                figures01[row*SIZE + col] =
                        MutationFactory.getMutantSprite(sprites[0], new Position(row, col));
            }
        }

    }

}
