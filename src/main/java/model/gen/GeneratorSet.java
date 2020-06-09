package model.gen;

import app.AppSettings;
import javafx.util.Pair;
import model.FigureType;
import model.MutationFactory;
import model.Sprite;
import model.StandardFactory;
import model.support.Position;
import model.support.UnsupportedTypeException;
import neuro.KohonenLayer;

import java.util.LinkedList;
import java.util.List;

public class GeneratorSet {

    public static List<Sprite> getNRandomSprites(Sprite base, int count, int mutations) {
        List<Sprite> list = new LinkedList<>();
        for (int i = 0; i < count; ++i) {
            list.add(MutationFactory.getMutantSprite(base, mutations));
        }
        return list;
    }

    public static List<Sprite> getNDetermineSprites(Sprite base, int count, KohonenLayer layer) {
        List<Sprite> list = new LinkedList<>();
        for (int i = 0; i < count; ++i) {
            int[] positions = layer.getDeterminativePositions(count,
                    layer.getRandomOtherGroupIndex(base));
            List<Position> positionList = new LinkedList<>();
            for (int p : positions) {
                positionList.add(Position.getPositionFromFlatIndex(p, AppSettings.BASE_SIZE));
            }
            list.add(MutationFactory.getMutantSprite(base, positionList));
        }
        return list;
    }

    public static List<Sprite> getBaseSet() {
        List<Sprite> sprites = new LinkedList<>();
        try {
            sprites.add(StandardFactory.getSprite(7, FigureType.UP));
            sprites.add(StandardFactory.getSprite(7, FigureType.DOWN));
            sprites.add(StandardFactory.getSprite(7, FigureType.LEFT));
            sprites.add(StandardFactory.getSprite(7, FigureType.RIGHT));
        } catch (UnsupportedTypeException e) {
            e.printStackTrace();
        }
        return sprites;
    }

    public static List<Sprite> getLearningSetFromBase(Sprite base) {
        List<Sprite> learningSet = new LinkedList<>();
        for (int row = 0; row < AppSettings.BASE_SIZE; ++row) {
            for (int col = 0; col < AppSettings.BASE_SIZE; ++col) {
                learningSet.add(MutationFactory.getMutantSprite(base, new Position(row, col)));
            }
        }
        return learningSet;
    }


    public static List<Pair<List<Sprite>, List<Sprite>>> getConfigurationList() {
        List<Pair<List<Sprite>, List<Sprite>>> result = new LinkedList<>();
        for (FigureType t : FigureType.values()) {
            result.add(getConfigList(t));
        }
        return result;
    }

    private static Pair<List<Sprite>, List<Sprite>> getConfigList(FigureType type) {

        List<Sprite> rightList = new LinkedList<>();
        List<Sprite> wrongList = new LinkedList<>();

        for (FigureType t : FigureType.values()) {
            for (int row = 0; row < AppSettings.BASE_SIZE; ++row) {
                for (int col = 0; col < AppSettings.BASE_SIZE; ++col) {
                    try {
                        if (type.equals(t)) {
                            rightList.add(MutationFactory.getMutantSprite(StandardFactory.getSprite(7, t), new Position(row, col)));
                        } else {
                            wrongList.add(MutationFactory.getMutantSprite(StandardFactory.getSprite(7, t), new Position(row, col)));
                        }
                    } catch (UnsupportedTypeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Pair<List<Sprite>, List<Sprite>> pair = new Pair<>(rightList, wrongList);
        return pair;
    }

}
