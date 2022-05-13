package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.*;

public class BlackPlayer extends Player {

    public BlackPlayer(String _name)
    {
        x=1* tileWidth;
        y=20*tileHeight;
        name=_name;
        lives =3;
    }

    @Override
    public void draw(){
        {
            if(right)
            {
                Drawer.draw(x,y, Assets.blackNinjaRight,
                        playerWidth * playerDrawScale,
                        playerHeight * playerDrawScale);
            }
            else
            {
                Drawer.draw(x,y,Assets.blackNinjaLeft,
                        playerWidth * playerDrawScale,
                        playerHeight * playerDrawScale);
            }

            if(endAtack==false)
            {
                Drawer.draw(x-32,y-32,Assets.ShurikenBg, shurikenCirclePixels, shurikenCirclePixels);
            }
        }
    }


}
