package PAOO_GAME.Map.Powers;

import PAOO_GAME.Drawable;

public abstract class Powers implements Drawable {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean visible=true;


    public Powers(int _x,int _y, int _width, int _height) {
        x=_x;
        y=_y;
        width=_width;
        height=_height;
    }

    public abstract void power();

    public void update() {
        power();
    }

    public abstract void draw();
}
