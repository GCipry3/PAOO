package PAOO_GAME.Map.Powers;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Graphics.Assets.rock2;

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
            Drawer.draw(x,y, rock2,width,height);
        }
    }
}
