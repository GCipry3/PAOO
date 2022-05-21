package PAOO_GAME.Map.Powers;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Graphics.Assets.grass;

public class Grass extends Powers{
    public Grass(int _x, int _y, int _width, int _height) {
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
            Drawer.draw(x,y, grass,width,height);
        }
    }
}
