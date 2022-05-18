package PAOO_GAME.Enemy;

import PAOO_GAME.Drawable;
import PAOO_GAME.Game;

import static PAOO_GAME.Constants.*;

public abstract class Enemy implements  Drawable {
    protected int x;
    protected int y;
    protected int firstX;//the enemy will go left and right
    protected int firstY;//initial positions
    protected boolean visible=true;
    protected int state=0;

    protected abstract void attack();
    public abstract void update();
    public abstract void draw();


    protected Enemy(int x,int y){
        firstX=x;
        firstY=y;
        this.x=x;
        this.y=y;
    }

    protected void move(){
        //the implementation is similar with a state machine
        if(x<firstX-enemyMaxMove){
            state=1;
        }
        if(x>firstX+enemyMaxMove){
            state=0;
        }
        switch (state) {
            case 0 -> moveLeftState();
            case 1 -> moveRightState();
        }
    }

    protected void moveLeftState(){
        x-=3;
    }
    protected void moveRightState(){
        x+=3;
    }

    protected boolean playerClose(){
        int xPlayer= Game.getInstance().player.getX();
        int yPlayer= Game.getInstance().player.getY();

        return (x - xPlayer < 10 * tileWidth) &&
                (x - xPlayer > -10 * tileWidth) &&
                (y - yPlayer < 3 * tileHeight) &&
                (y - yPlayer > -3 * tileHeight);
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
}
