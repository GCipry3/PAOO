package PAOO_GAME.Map.Powers;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Graphics.Assets.coin;

public class Coin extends Powers{
    public Coin(int _x, int _y, int _width, int _height) {
        super(_x, _y, _width, _height);
    }

    @Override
    public void power() {

        if(visible && collectFromAttackCheck())
        {
            playerIncreaseCoinsCollected();
            visible=false;
        }
        //System.out.println(Collision.checkCollision(player.x,player.y, player.getWidth(), player.getHeight(), x,y, width, height));

    }

    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y, coin,width,height);
        }
    }

}
