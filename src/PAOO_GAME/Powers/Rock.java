package PAOO_GAME.Powers;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;

public class Rock extends Powers{
    public Rock(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {
        //
    }

    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y, Assets.rock2,width,height);
        }
    }
}
