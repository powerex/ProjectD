package app;

import model.Figure;
import model.Sprite;
import view.ConsoleSpriteRender;
import view.FileSpriteRender;
import view.SpriteRender;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App5 {




    public static void main(String[] args) {
        final int CELL_SIZE = 10;
        int size = CELL_SIZE * AppSettings.BASE_SIZE;

        File img = new File("ExperimentSet/GroupRandom5/Random5_DOWN_01.bmp");
        BufferedImage buffImg =
                new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        FileSpriteRender render = new FileSpriteRender();

        try {
            buffImg = ImageIO.read(img);
            Sprite s = new Figure(render.getTableFromImage(buffImg));
            SpriteRender console = new ConsoleSpriteRender();
            console.render(s);
        }
        catch (IOException e) { }



    }

}
