package app;

import model.*;
import neuro.KohonenLayer;

public class App4 {

    public static void main(String[] args) {

        Sprite[] base = new Sprite[4];
        base[0] = new BaseImage(AppSettings.BASE_SIZE);
        base[1] = new BaseImageCW(AppSettings.BASE_SIZE);
        base[2] = new BaseImageCCW(AppSettings.BASE_SIZE);
        base[3] = new BaseImageR(AppSettings.BASE_SIZE);

        KohonenLayer kl = new KohonenLayer();

        System.out.println(kl);

        kl.learn(base[0]);


    }

}
