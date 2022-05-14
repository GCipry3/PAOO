package PAOO_GAME.Enemy;

import PAOO_GAME.Drawable;
import PAOO_GAME.Updatable;

import static PAOO_GAME.Game.player;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public abstract class Enemy implements Updatable, Drawable {
    protected int x;
    protected int y;
    protected int firstX;//inamicul se va deplasa in stanga si dreapta
    protected int firstY;//pozitiei initiale

    protected abstract void move();
    protected abstract void attack();
    protected boolean playerClose(){
        int xPlayer=player.getX();
        int yPlayer=player.getY();
        if(sqrt(pow(x-xPlayer,2)-pow(y-yPlayer,2))<75){
            return true;
        }
        return false;
    }
}
