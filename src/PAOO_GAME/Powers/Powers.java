package PAOO_GAME.Powers;

import PAOO_GAME.Drawable;

public abstract class Powers implements Drawable {
    public int x=0;
    public int y=0;
    public int width=0;
    public int height=0;
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
