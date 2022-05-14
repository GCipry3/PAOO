package PAOO_GAME.Enemy;

import static PAOO_GAME.Constants.enemyMaxMove;

public class Ogre extends Enemy{
    @Override
    public void move() {
        if (x < firstX - enemyMaxMove)
        {
            x++;
        }else if(x > firstX + enemyMaxMove){
            x--;
        }else if(x <= firstX){
            x--;
        }else{
            x++; //x>firstX
        }
    }

    @Override
    public void attack() {
        if(playerClose()){

        }
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {

    }
}
