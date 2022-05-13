package PAOO_GAME.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.Player;

import static PAOO_GAME.Game.player;
import static PAOO_GAME.Player.Player.endAtack;
import static PAOO_GAME.Player.Player.shurikenCircle;

public class Gold extends Powers{
    public Gold(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {

        if(visible==true && endAtack == false && Collision.checkCollision(player.x-32,player.y-32, shurikenCircle, shurikenCircle, x,y, width, height) ) {

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
