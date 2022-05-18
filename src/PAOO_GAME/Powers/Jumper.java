package PAOO_GAME.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Constants;
import PAOO_GAME.Game;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.Player;

import static PAOO_GAME.Constants.jumpHeight;

public class Jumper extends Powers{
    public Jumper(int x,int y, int _width, int _height)
    {
        super(x,y,_width,_height);
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
            jumpHeight+=32;
            visible=false;
        }
        //System.out.println(Collision.checkCollision(player.x,player.y, player.getWidth(), player.getHeight(), x,y, width, height));
    }
    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y+15, Assets.jumpRight,width,height);
        }
    }

}
