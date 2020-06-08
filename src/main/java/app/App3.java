package app;

import model.FigureType;
import model.Sprite;
import model.StandardFactory;
import model.support.UnsupportedTypeException;
import neuro.GrosbergNeuron;
import neuro.KohonenNeuron;
import view.ConsoleSpriteRender;
import view.SpriteRender;

import java.util.LinkedList;
import java.util.List;

public class App3 {

    public static void main(String[] args) throws UnsupportedTypeException {

        SpriteRender console = new ConsoleSpriteRender();
        console.render(StandardFactory.getSprite(7, FigureType.UP));
        console.render(StandardFactory.getSprite(7, FigureType.DOWN));
        console.render(StandardFactory.getSprite(7, FigureType.LEFT));
        console.render(StandardFactory.getSprite(7, FigureType.RIGHT));

    }

}
