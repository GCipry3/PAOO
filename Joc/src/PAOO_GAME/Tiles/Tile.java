package PAOO_GAME.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

import PAOO_GAME.Component.Coord;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets.*;

public class Tile extends Component {
    private static int NR_MAX=30;
    public static int tileWidth = 32;
    public static int tileHeight = 32;

    public static Tile diamond=new Diamond(0);
    public static Tile gold=new Gold(1);
    public static Tile jumperLeft=new JumperLeft(2);
    public static Tile jumperRight=new JumperRight(3);

    protected int scale;
    protected int id;

    protected BufferedImage img;

    Tile(BufferedImage _img,int _id)
    {
        img=_img;
        id=_id;
    }

    public void draw(Coord pos)
    {
        Drawer.draw(pos,img,tileWidth*scale,tileHeight*scale);
    }


}
