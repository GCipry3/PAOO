package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.*;

public class BlackPlayer extends Player {

    public BlackPlayer()
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
                        Assets.blackNinjaRight,
                        playerWidth,
                        playerHeight
                );
            }
            else
            {
                Drawer.draw(
                        x,y,
                        Assets.blackNinjaLeft,
                        playerWidth,
                        playerHeight
                );
            }

            if(getEndAttack())
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
