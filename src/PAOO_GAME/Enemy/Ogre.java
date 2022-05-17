package PAOO_GAME.Enemy;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.enemyHeight;
import static PAOO_GAME.Constants.enemyWidth;

public class Ogre extends Enemy{
    public Ogre(int x, int y) {
        super(x, y);
    }

    @Override
    protected void attack() {
        //TODO
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public void draw() {
        Drawer.draw(x,y, Assets.firstOgre, enemyWidth, enemyHeight);
    }
}
