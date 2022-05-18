package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Constants.shurikenCirclePixels;

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
                        Assets.blueNinjaRight,
                        playerWidth,
                        playerHeight
                );
            }
            else
            {
                Drawer.draw(
                        x,y,
                        Assets.blueNinjaLeft,
                        playerWidth,
                        playerHeight
                );
            }

            if(getAttackStatus())
            {
                Drawer.draw(
                        x-32,y-32,
                        Assets.ShurikenBg,
                        shurikenCirclePixels,
                        shurikenCirclePixels
                );
            }

            listOfShurikens.forEach(ShinobiShuriken::draw);
        }
    }
}
