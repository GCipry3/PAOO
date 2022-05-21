package PAOO_GAME.Map.Powers;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Graphics.Assets.gold;


public class Gold extends Powers{
    public Gold(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {


        if(visible && collectFromAttackCheck())
        {
            gameSetGoldCollectedFlag();
            visible=false;
        }
    }

    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y, gold,width,height);
        }
    }

}
