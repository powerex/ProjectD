package app;

import model.*;
import model.support.IncorrectLengthException;
import model.support.NotDetermineSystemException;
import model.support.Position;
import model.support.UnsupportedTypeException;
import neuro.GrosbergLayer;
import neuro.KohonenLayer;
import view.ConsoleSpriteRender;
import view.SpriteRender;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class App5 {

    public static void main(String[] args) throws IncorrectLengthException, UnsupportedTypeException {

        List<Sprite> sprites = new LinkedList<>();
        sprites.add(StandardFactory.getSprite(7, FigureType.UP));
        sprites.add(StandardFactory.getSprite(7, FigureType.DOWN));
        sprites.add(StandardFactory.getSprite(7, FigureType.LEFT));
        sprites.add(StandardFactory.getSprite(7, FigureType.RIGHT));

        KohonenLayer kl = new KohonenLayer(sprites);

        SpriteRender console = new ConsoleSpriteRender();
        List<Sprite> learningSet = new LinkedList<>();

        for (Sprite s: sprites) {
            learningSet.clear();
            for (int row = 0; row < AppSettings.BASE_SIZE; ++row) {
                for (int col = 0; col < AppSettings.BASE_SIZE; ++col) {
                    learningSet.add(MutationFactory.getMutantSprite(s, new Position(row, col)));
                }
            }
            kl.batchLearn(learningSet);
        }

        System.out.println("\n~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n");
        System.out.println(kl);

        learningSet.clear();
        for (int row = 0; row < AppSettings.BASE_SIZE; ++row) {
            for (int col = 0; col < AppSettings.BASE_SIZE; ++col) {
                try {
                    learningSet.add(MutationFactory.getMutantSprite(StandardFactory.getSprite(7, FigureType.UP), new Position(row, col)));
                } catch (UnsupportedTypeException e) {
                    e.printStackTrace();
                }
            }
        }
        List<Sprite> wrongList = new LinkedList<>();
        for (int row = 0; row < AppSettings.BASE_SIZE; ++row) {
            for (int col = 0; col < AppSettings.BASE_SIZE; ++col) {
                try {
                    wrongList.add(MutationFactory.getMutantSprite(StandardFactory.getSprite(7, FigureType.LEFT), new Position(row, col)));
                    wrongList.add(MutationFactory.getMutantSprite(StandardFactory.getSprite(7, FigureType.RIGHT), new Position(row, col)));
                    wrongList.add(MutationFactory.getMutantSprite(StandardFactory.getSprite(7, FigureType.DOWN), new Position(row, col)));
                } catch (UnsupportedTypeException e) {
                    e.printStackTrace();
                }
            }
        }

        int[] det = kl.getDeterminativePositions(10, 0);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Arrays.stream(det).forEach(System.out::println);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        try {
            kl.calcTheta(learningSet, wrongList);
        } catch (NotDetermineSystemException e) {
            e.printStackTrace();
        }

        GrosbergLayer gl = new GrosbergLayer();

        gl.learn(kl.getTriggeredRecognizedVector(StandardFactory.getSprite(7, FigureType.UP)),
                 StandardFactory.getSprite(3, FigureType.UP));
        gl.learn(kl.getTriggeredRecognizedVector(StandardFactory.getSprite(7, FigureType.DOWN)),
                StandardFactory.getSprite(3, FigureType.DOWN));
        gl.learn(kl.getTriggeredRecognizedVector(StandardFactory.getSprite(7, FigureType.LEFT)),
                StandardFactory.getSprite(3, FigureType.LEFT));
        gl.learn(kl.getTriggeredRecognizedVector(StandardFactory.getSprite(7, FigureType.RIGHT)),
                StandardFactory.getSprite(3, FigureType.RIGHT));

        System.out.println(gl);
        System.out.println("=============================================================");

        Sprite in = MutationFactory.getMutantSprite(StandardFactory.getSprite(7, FigureType.DOWN), 10);
        console.render(in);
        Sprite test = gl.getSprite(kl.getTriggeredRecognizedVector(in));
        console.render(test);



    }

}
