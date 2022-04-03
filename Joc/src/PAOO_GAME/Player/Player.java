package PAOO_GAME.Player;

import PAOO_GAME.Component.Coord;
import PAOO_GAME.Tiles.Tile;

public abstract class Player {

    protected String name;
    protected int lives;
    public Coord pos=new Coord(1* Tile.tileWidth,20*Tile.tileHeight);

    protected int speed=3;
    protected int width=32;
    protected int height=32;
    protected int scale= 2;
    public int jumpScale=5;
    public int jumpTimes=3;

    public abstract String getName();
    public abstract int getLives();
    public abstract void Init();
    public abstract void atack();
    public abstract void draw();
    public abstract void update();
    public abstract boolean increaseLife();
    public Coord getCoords(){return pos;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
}
