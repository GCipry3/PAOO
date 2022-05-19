package PAOO_GAME.Enemy;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.enemyHeight;
import static PAOO_GAME.Constants.enemyWidth;

public class Ogre extends Goblin{

    public Ogre(int x, int y) {
        super(x, y);
        damage=30;
    }

    @Override
    public void draw() {
        if(visible) {
            if(state==1){
                Drawer.draw(x, y, Assets.ogreRight.get(random), enemyWidth, enemyHeight);
            }else{
                Drawer.draw(x, y, Assets.ogreLeft.get(random), enemyWidth, enemyHeight);
            }
            listOfProjectiles.forEach(Projectile::draw);
        }
    }
}
