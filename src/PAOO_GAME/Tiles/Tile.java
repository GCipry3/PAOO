package PAOO_GAME.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

import PAOO_GAME.Component.Drawer;

public class Tile extends Component {
    private static int NR_MAX=30;
    public static int tileWidth = 32;
    public static int tileHeight = 32;

    protected int scale;
    protected int id;

    protected BufferedImage img;

    Tile(BufferedImage _img,int _id)
    {
        img=_img;
        id=_id;
    }

    public void draw(int x, int y)
    {
        Drawer.draw(x,y,img,tileWidth*scale,tileHeight*scale);
    }


}
