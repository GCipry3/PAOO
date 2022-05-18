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
            switch (random) {
                case 0 -> Drawer.draw(x, y, Assets.firstOgre, enemyWidth, enemyHeight);
                case 1 -> Drawer.draw(x, y, Assets.secondOgre, enemyWidth, enemyHeight);
                case 2 -> Drawer.draw(x, y, Assets.thirdOgre, enemyWidth, enemyHeight);
                default -> throw new RuntimeException(Integer.toString(random));
            }
            listOfProjectiles.forEach(Projectile::draw);
        }
    }
}
