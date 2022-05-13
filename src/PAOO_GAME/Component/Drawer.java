package PAOO_GAME.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PAOO_GAME.Game.g;

public class Drawer {
    public static void draw(int x,int y, BufferedImage img,int width,int height)
    {
        g.drawImage(img, x, y, width, height, null);
    }
}
