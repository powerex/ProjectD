package app;

import model.FigureType;
import model.MutationFactory;
import model.Sprite;
import model.StandardFactory;
import model.gen.GeneratorSet;
import model.support.UnsupportedTypeException;
import view.ConsoleSpriteRender;
import view.FileSpriteRender;
import view.SpriteRender;

import java.util.List;

public class App {

    public static void main(String[] args) throws UnsupportedTypeException {

        SpriteRender console = new ConsoleSpriteRender();
        FileSpriteRender file = new FileSpriteRender();


        NeuronNet net = new NeuronNet();
        Sprite toRecognize = MutationFactory.getMutantSprite(StandardFactory.getSprite(7, FigureType.LEFT), 10);
//        Sprite toRecognize = StandardFactory.getSprite(7, FigureType.RIGHT);
        Sprite recognized = net.recognize(toRecognize);


        console.render(toRecognize);
        System.out.println("====================================");
        console.render(recognized);

        file.render(toRecognize);

//        for (FigureType type : FigureType.values()) {
//            file.saveImages(type.getName(), "LearningSet/Group_"+type.getName(), GeneratorSet.getLearningSetFromBase(StandardFactory.getSprite(AppSettings.BASE_SIZE, type)));
//        }

        /*
        for (FigureType type : FigureType.values()) {
            file.saveImages("Random5_"+type.getName()+"_", "ExperimentSet/GroupRandom5", GeneratorSet.getNRandomSprites(StandardFactory.getSprite(AppSettings.BASE_SIZE, type), 6, 5));
        }

        for (FigureType type : FigureType.values()) {
            file.saveImages("Random10_"+type.getName()+"_", "ExperimentSet/GroupRandom10", GeneratorSet.getNRandomSprites(StandardFactory.getSprite(AppSettings.BASE_SIZE, type), 6, 10));
        }

        for (FigureType type : FigureType.values()) {
            file.saveImages("Random15_"+type.getName()+"_", "ExperimentSet/GroupRandom15", GeneratorSet.getNRandomSprites(StandardFactory.getSprite(AppSettings.BASE_SIZE, type), 6, 15));
        }
        */


    }

}
