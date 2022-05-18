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
            switch(random){
            case 0:
                Drawer.draw(x, y, Assets.firstOgre, enemyWidth, enemyHeight);
                break;
            case 1:
                Drawer.draw(x, y, Assets.secondOgre, enemyWidth, enemyHeight);
                break;
            case 2:
                Drawer.draw(x, y, Assets.thirdOgre, enemyWidth, enemyHeight);
                break;
            default: throw new RuntimeException(Integer.toString(random));
        }
            for(int i=0;i<listOfProjectiles.size();i++){
                listOfProjectiles.get(i).draw();
            }
        }
    }
}
