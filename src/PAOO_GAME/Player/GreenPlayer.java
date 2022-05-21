package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Graphics.Assets.*;

public class GreenPlayer extends Player {
    public GreenPlayer()
    {
        super();
        lifeStatus=500;
    }

    @Override
    public void draw(){
        {
            if(right)
            {
                Drawer.draw(
                        x,y,
                        greenNinjaRight,
                        playerWidth,
                        playerHeight
                );
            }
            else
            {
                Drawer.draw(
                        x,y,
                        greenNinjaLeft,
                        playerWidth,
                        playerHeight
                );
            }

            if(getAttackStatus())
            {
                Drawer.draw(
                        x-32,y-32,
                        ShurikenBg,
                        shurikenCirclePixels,
                        shurikenCirclePixels
                );
            }

            drawShurikens();
        }
    }
}
