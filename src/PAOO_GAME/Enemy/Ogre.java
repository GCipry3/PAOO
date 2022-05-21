package PAOO_GAME.Enemy;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Constants.enemyHeight;
import static PAOO_GAME.Constants.enemyWidth;
import static PAOO_GAME.Graphics.Assets.ogreLeft;
import static PAOO_GAME.Graphics.Assets.ogreRight;

public class Ogre extends Goblin{

    public Ogre(int x, int y) {
        super(x, y);
        damage=30;
    }

    @Override
    public void draw() {
        if(visible) {
            if(state==1){
                Drawer.draw(x, y, ogreRight.get(random), enemyWidth, enemyHeight);
            }else{
                Drawer.draw(x, y, ogreLeft.get(random), enemyWidth, enemyHeight);
            }
            drawProjectiles();
        }
    }

}
