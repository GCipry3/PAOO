package PAOO_GAME.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PAOO_GAME.Game.g;

public class Drawer {
    public static void draw(Coord pos, BufferedImage img,int width,int height)
    {
        g.drawImage(img, pos.getX(), pos.getY(), width, height, null);
    }
}
