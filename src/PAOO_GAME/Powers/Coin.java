package PAOO_GAME.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Constants;
import PAOO_GAME.Game;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.Player;

public class Coin extends Powers{
    public Coin(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {

        if(visible &&
                Player.getAttackStatus() &&
                Collision.checkCollision(
                        Game.getInstance().player.getX() -32,
                        Game.getInstance().player.getY() -32,
                        Constants.shurikenCirclePixels,
                        Constants.shurikenCirclePixels,
                        x,y, width, height) )
        {
            Game.getInstance().player.increaseCoins();
            visible=false;
        }
        //System.out.println(Collision.checkCollision(player.x,player.y, player.getWidth(), player.getHeight(), x,y, width, height));

    }

    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y, Assets.coin,width,height);
        }
    }

}
