package visual;

import app.AppSettings;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
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

    final FileChooser fileChooser = new FileChooser();

    @FXML
    public void click(ActionEvent actionEvent) throws IOException {

        final int CELL_SIZE = 10;
        int size = CELL_SIZE * AppSettings.BASE_SIZE;

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

                FileInputStream input = new FileInputStream(fileEntry.getAbsolutePath());
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);

                Label label = new Label();
                label.setText(fileEntry.getName().replaceFirst("[.][^.]+$", ""));



                grid.add(label, 0, r);
                grid.add(imageView, 1, r);

                r++;
            }
        }



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
