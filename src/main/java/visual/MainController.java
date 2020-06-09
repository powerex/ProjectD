package visual;

import app.AppSettings;
import model.NeuronNet;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.*;
import model.gen.GeneratorSet;
import model.support.UnsupportedTypeException;
import view.FileSpriteRender;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    AnchorPane anchor;

    @FXML
    TextField textDir;

    @FXML
    Button button;

    @FXML
    GridPane grid;

    @FXML
    Button start;

    FileSpriteRender fileRender = new FileSpriteRender();
    NeuronNet net;

    @FXML
    public void click(ActionEvent actionEvent) throws IOException {

        final int CELL_SIZE = 10;
        int size = CELL_SIZE * AppSettings.BASE_SIZE;

        grid.getRowConstraints().clear();
        grid.getChildren().clear();

        final DirectoryChooser dirchooser = new DirectoryChooser();
        Stage stage = (Stage) anchor.getScene().getWindow();
        File file = dirchooser.showDialog(null);
        if (file != null) {
            textDir.setText(file.getAbsolutePath());
            int r = 0;
            for (final File fileEntry : file.listFiles()) {
                grid.getRowConstraints().add(new RowConstraints(100));

                File img = new File(fileEntry.getAbsolutePath());
                BufferedImage buffImg =
                        new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
                buffImg = ImageIO.read(img);
                Sprite s = new Figure(fileRender.getTableFromImage(buffImg));

                Sprite recognized = net.recognize(s);
                BufferedImage buf = fileRender.renderImage(recognized, AppSettings.OUT_SIZE);

                Image view = SwingFXUtils.toFXImage(buf, null);
                ImageView reco = new ImageView(view);

                FileInputStream input = new FileInputStream(fileEntry.getAbsolutePath());
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);

                Label label = new Label();
                label.setText(fileEntry.getName().replaceFirst("[.][^.]+$", ""));

                grid.add(reco, 2, r);
                grid.add(label, 0, r);
                grid.add(imageView, 1, r);

                r++;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startClick(ActionEvent actionEvent) throws UnsupportedTypeException {
        net = new NeuronNet();

        //*
        File dirLearning = new File("LearningSet");
        if (! dirLearning.exists()) {
            dirLearning.mkdir();
        }

        File dirExperiment = new File("ExperimentSet");
        if (! dirExperiment.exists()) {
            dirExperiment.mkdir();
        }

        for (FigureType type : FigureType.values()) {
            fileRender.saveImages(type.getName(), "LearningSet/Group_"+type.getName(), GeneratorSet.getLearningSetFromBase(StandardFactory.getSprite(AppSettings.BASE_SIZE, type)));
        }

        for (FigureType type : FigureType.values()) {
            fileRender.saveImages("Random5_"+type.getName()+"_", "ExperimentSet/GroupRandom5", GeneratorSet.getNRandomSprites(StandardFactory.getSprite(AppSettings.BASE_SIZE, type), 6, 5));
        }

        for (FigureType type : FigureType.values()) {
            fileRender.saveImages("Random10_"+type.getName()+"_", "ExperimentSet/GroupRandom10", GeneratorSet.getNRandomSprites(StandardFactory.getSprite(AppSettings.BASE_SIZE, type), 6, 10));
        }

        for (FigureType type : FigureType.values()) {
            fileRender.saveImages("Random15_"+type.getName()+"_", "ExperimentSet/GroupRandom15", GeneratorSet.getNRandomSprites(StandardFactory.getSprite(AppSettings.BASE_SIZE, type), 6, 15));
        }
        //*/

        start.setDisable(true);
        button.setDisable(false);
    }
}
