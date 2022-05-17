package PAOO_GAME.Enemy;

import PAOO_GAME.Drawable;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;

public abstract class Enemy implements  Drawable {
    protected int x;
    protected int y;
    protected int firstX;//inamicul se va deplasa in stanga si dreapta
    protected int firstY;//pozitiei initiale
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
        //implementarea este asemanatoare unui state machine
        if(x<firstX-enemyMaxMove){
            state=1;
        }
        if(x>firstX+enemyMaxMove){
            state=0;
        }
        switch (state){
            case 0:
                moveLeftState();
                break;
            case 1:
                moveRightState();
                break;
        }
    }

    protected void moveLeftState(){
        x-=3;
    }
    protected void moveRightState(){
        x+=3;
    }

    protected boolean playerClose(){
        int xPlayer=player.getX();
        int yPlayer=player.getY();

        if((x-xPlayer<6*tileWidth) && (x-xPlayer>-6*tileWidth) &&
          (y-yPlayer<2*tileHeight) && (y-yPlayer>-2*tileHeight)){
            //System.out.println((x-xPlayer)+"|"+(y-yPlayer));
            return true;
        }
        return false;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
}
