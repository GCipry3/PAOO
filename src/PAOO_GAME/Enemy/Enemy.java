package PAOO_GAME.Enemy;

import PAOO_GAME.Player.Player;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public abstract class Enemy extends Player {
    private boolean isClose=false;

    protected abstract void move(int x1,int x2,Player player);
    protected abstract void atack(Player player);
    protected boolean playerClose(Player player){
        int xPlayer=player.getX();
        int yPlayer=player.getY();
        if(sqrt(pow(x-xPlayer,2)-pow(y-yPlayer,2))<75){
            return true;
        }
        return false;
    }
}
