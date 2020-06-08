package app;

import model.BaseImage;
import model.MutationFactory;
import model.Sprite;
import view.ConsoleSpriteRender;
import view.SpriteRender;

public class App2 {

    private static final int SIZE = 7;
    private static final int SERIES_SIZE = 6;
    private static final int RAND_COUNT = 15;

    public static void main(String[] args) {

        Sprite s = new BaseImage(SIZE);
        Sprite[] series = new Sprite[SERIES_SIZE];

        SpriteRender render = new ConsoleSpriteRender();

        for (int i=0; i<SERIES_SIZE; ++i) {
            series[i] = MutationFactory.getMutantSprite(s, RAND_COUNT);
            render.render(series[i]);
        }

    }

}
