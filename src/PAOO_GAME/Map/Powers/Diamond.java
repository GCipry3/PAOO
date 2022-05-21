package PAOO_GAME.Map.Powers;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Graphics.Assets.diamond;

public class Diamond extends Powers{
    public Diamond(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {


        if(visible && collectFromAttackCheck())
        {
            gameWindowsAddShurikensToCounter(5);
            visible=false;
        }
    }

    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y, diamond,width,height);
        }
    }

}
