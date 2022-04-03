package PAOO_GAME.Powers;

import PAOO_GAME.Component.Coord;
import PAOO_GAME.Player.Player;

public abstract class Powers {
    public Coord pos=new Coord(0,0);
    public int width=0;
    public int height=0;
    public boolean visible=true;


    public Powers(Coord _pos, int _width, int _height) {
        pos.set(_pos.getX(),_pos.getY());
        width=_width;
        height=_height;
    }

    public abstract void power(Player player);
    public abstract void update(Player player);
    public abstract void draw(Player player);
}
