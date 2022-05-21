package PAOO_GAME.Map.Powers;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Graphics.Assets.jumpRight;

public class Jumper extends Powers{
    public Jumper(int x,int y, int _width, int _height)
    {
        super(x,y,_width,_height);
    }

    @Override
    public void power() {

        if(visible && collectFromAttackCheck())
        {
            increasePlayerHeight(32);
            visible=false;
        }
        //System.out.println(Collision.checkCollision(player.x,player.y, player.getWidth(), player.getHeight(), x,y, width, height));
    }
    @Override
    public void draw() {
        if(visible)
        {
            Drawer.draw(x,y+15, jumpRight,width,height);
        }
    }

}
