package PAOO_GAME.Powers;

import PAOO_GAME.Component.Coord;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.Collision;
import PAOO_GAME.Player.KeyboardControl;
import PAOO_GAME.Player.Player;

public class Jumper extends Powers{
    private int cnt=0;
    private int type=1;
    public Jumper(Coord _pos, int _width, int _height)
    {
        super(_pos,_width,_height);
    }

    @Override
    public void power(Player player) {

        if(visible==true && KeyboardControl.velocity.getY()==-1) {
            if (Collision.checkCollision(player.getCoords(), player.getWidth(), player.getHeight(), pos, width, height) ) {
                player.jumpScale = 7;
                player.jumpTimes = 7;
                visible=false;
            }
        }
        if(visible==false)
        {
            cnt++;
            if(cnt > 180)
            {
                visible=true;
                player.jumpTimes=3;
                player.jumpScale=5;
                cnt=0;
            }
        }
    }

    @Override
    public void update(Player player) {
        power(player);

    }

    @Override
    public void draw(Player player) {
        if(visible==true)
        {
            Drawer.draw(pos, Assets.jumpRight,width,height);
        }
    }
}
