package PAOO_GAME.Map.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Constants;
import PAOO_GAME.Game;
import PAOO_GAME.Graphics.Assets;

public class CheckPoint extends Powers{
    public CheckPoint(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {

        if(visible && Collision.checkCollision(
                        Game.getInstance().player.getX(),
                        Game.getInstance().player.getY(),
                        Constants.playerWidth,
                        Constants.playerHeight,
                        x,y, width, height) )
        {
            visible=false;
        }

    }

    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y, Assets.checkpoint,width,height);
        }
    }
}
