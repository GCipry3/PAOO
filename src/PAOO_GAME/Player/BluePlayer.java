package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Graphics.Assets.*;

public class BluePlayer extends Player{
    public BluePlayer()
    {
        super();
    }

    @Override
    public void draw(){
        {
            if(right)
            {
                Drawer.draw(
                        x,y,
                        blueNinjaRight,
                        playerWidth,
                        playerHeight
                );
            }
            else
            {
                Drawer.draw(
                        x,y,
                        blueNinjaLeft,
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
