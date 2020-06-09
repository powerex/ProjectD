package model;

import app.AppSettings;
import javafx.util.Pair;
import model.gen.GeneratorSet;
import model.support.IncorrectLengthException;
import model.support.NotDetermineSystemException;
import model.support.UnsupportedTypeException;
import neuro.GrosbergLayer;
import neuro.KohonenLayer;

import java.util.List;

public class NeuronNet {

    private KohonenLayer kohonenLayer;
    private GrosbergLayer grosbergLayer;

    public NeuronNet() {
        List<Sprite> sprites = GeneratorSet.getBaseSet();
        try {
            kohonenLayer = new KohonenLayer(sprites);
        } catch (IncorrectLengthException e) {
            e.printStackTrace();
        }

        for (Sprite s: sprites) {
            List<Sprite> learningSet = GeneratorSet.getLearningSetFromBase(s);
            kohonenLayer.batchLearn(learningSet);
        }

        List<Pair<List<Sprite>, List<Sprite>>> configurationList =
            GeneratorSet.getConfigurationList();
        for (Pair<List<Sprite>, List<Sprite>> pair: configurationList) {
            try {
                kohonenLayer.calcTheta(pair.getKey(), pair.getValue());
            } catch (IncorrectLengthException | NotDetermineSystemException e) {
                e.printStackTrace();
            }
        }

        grosbergLayer = new GrosbergLayer();
        try {
        for (FigureType type : FigureType.values()) {
            grosbergLayer.study(kohonenLayer.getTriggeredRecognizedVector(StandardFactory.getSprite(AppSettings.BASE_SIZE, type)),
                    StandardFactory.getSprite(AppSettings.OUT_SIZE, type));
        }
        }catch (UnsupportedTypeException e) {
            e.printStackTrace();
        }

        grosbergLayer.info();
    }

    public Sprite recognize(Sprite sprite) {
        Vector vector = kohonenLayer.getTriggeredRecognizedVector(sprite);
        return grosbergLayer.getSprite(vector);
    }

}
