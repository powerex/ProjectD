package view;

import app.AppSettings;
import model.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileSpriteRender implements SpriteRender {

    private final int CELL_SIZE = 10;

    public BufferedImage renderImage(Sprite image, int dim) {
        int size = CELL_SIZE * dim;
        final BufferedImage res = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB );
        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                res.setRGB(col, row, (image.getPixel(row / CELL_SIZE, col / CELL_SIZE))?Color.BLACK.getRGB(): Color.WHITE.getRGB());
            }
        }
        return res;
    }

    public void saveImage(BufferedImage image, String name) {
        try {
            RenderedImage rendImage = image;
            ImageIO.write(rendImage, "bmp", new File(name + ".bmp"));
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImages(String name, String folder, List<Sprite> images) {
        File directory = new File(folder);
        if (! directory.exists()) {
            directory.mkdir();
        }
        for (int i=0; i<images.size(); ++i) {
            saveImage(renderImage(images.get(i), AppSettings.BASE_SIZE), folder + '/' + name + String.format("%02d",i+1));
        }
    }

    @Override
    public void render(Sprite image) {
//        saveImage(renderImage(image), "sprite");
    }

    public boolean[][] getTableFromImage(BufferedImage image) {
        int size = AppSettings.BASE_SIZE;
        boolean[][] table = new boolean[size][size];

        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                int color = image.getRGB(col*CELL_SIZE + 3, row*CELL_SIZE + 3);
                table[row][col] = color != -1;
            }
        }

        return table;
    }


}
