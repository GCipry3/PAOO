package PAOO_GAME.Map.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Constants;
import PAOO_GAME.Game;
import PAOO_GAME.GameWindow.GameWindow;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.Player;

public class Diamond extends Powers{
    public Diamond(int _x, int _y, int _width, int _height) {
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
            GameWindow.setShurikenCounter(GameWindow.getShurikenCounter()+5);
            visible=false;
        }
    }

    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y, Assets.diamond,width,height);
        }
    }

}
