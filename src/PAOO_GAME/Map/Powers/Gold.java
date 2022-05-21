package PAOO_GAME.Map.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Game;

import static PAOO_GAME.Constants.shurikenCirclePixels;
import static PAOO_GAME.Graphics.Assets.gold;


public class Gold extends Powers{
    public Gold(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {

        if(visible &&
                Game.getInstance().getPlayerAttackStatus() &&
                Collision.checkCollision(
                        Game.getInstance().getPlayerX() -32,
                        Game.getInstance().getPlayerY() -32,
                        shurikenCirclePixels,
                        shurikenCirclePixels,
                        x,y, width, height) )
        {
            Game.getInstance().playerSetGoldCollected();
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
