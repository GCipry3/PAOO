package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Graphics.Assets.*;

public class BlackPlayer extends Player {

    public BlackPlayer()
    {
        super();
        lifeStatus=750;
    }

    @Override
    public void draw(){
        {
            if(right)
            {
                Drawer.draw(
                        x,y,
                        blackNinjaRight,
                        playerWidth,
                        playerHeight
                );
            }
            else
            {
                Drawer.draw(
                        x,y,
                        blackNinjaLeft,
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
