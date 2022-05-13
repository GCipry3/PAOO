package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Map.Map;
import PAOO_GAME.Tiles.Tile;

public class BlackPlayer extends Player {

    public BlackPlayer(String _name)
    {
        x=1* Tile.tileWidth;
        y=20*Tile.tileHeight;
        name=_name;
        lives =3;
    }

    @Override
    public void draw(){
        {
            if(right)
            {
                Drawer.draw(x,y, Assets.blackNinjaRight,width*scale,height*scale);
            }
            else
            {
                Drawer.draw(x,y,Assets.blackNinjaLeft,width*scale,height*scale);
            }

            if(endAtack==false)
            {
                Drawer.draw(x-32,y-32,Assets.ShurikenBg,shurikenCircle,shurikenCircle);
            }
        }
    }


}
