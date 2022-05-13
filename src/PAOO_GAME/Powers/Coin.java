package PAOO_GAME.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Constants;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.Player;

import static PAOO_GAME.Game.player;

public class Coin extends Powers{
    public Coin(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {

        if(visible==true &&
                Player.getEndAtackStatus() == false &&
                Collision.checkCollision(
                        player.getX() -32,
                        player.getY() -32,
                        Constants.shurikenCirclePixels,
                        Constants.shurikenCirclePixels,
                        x,y, width, height) )
        {
            visible=false;
        }
        //System.out.println(Collision.checkCollision(player.x,player.y, player.getWidth(), player.getHeight(), x,y, width, height));

    }

    @Override
    public void draw() {
        if(visible==true)
        {
            Drawer.draw(x,y, Assets.coin,width,height);
        }
    }

}
