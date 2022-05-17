package PAOO_GAME.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Constants;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.Player;

import static PAOO_GAME.Game.player;

public class Gold extends Powers{
    public Gold(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {

        if(visible==true &&
                Player.getEndAttackStatus() == false &&
                Collision.checkCollision(
                        player.getX() -32,
                        player.getY() -32,
                        Constants.shurikenCirclePixels,
                        Constants.shurikenCirclePixels,
                        x,y, width, height) )
        {

            visible=false;
        }
    }

    @Override
    public void draw() {
        if(visible==true)
        {
            Drawer.draw(x,y, Assets.gold,width,height);
        }
    }

}
