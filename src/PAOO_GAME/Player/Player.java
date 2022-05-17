package PAOO_GAME.Player;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Drawable;
import PAOO_GAME.GameWindow.GameWindow;
import PAOO_GAME.Map.Map;

import java.util.ArrayList;
import java.util.List;

import static PAOO_GAME.Constants.*;

public abstract class Player implements  Drawable {

    protected int lifeStatus=100;

    protected static int x;
    protected static int y;

    protected boolean right=false;
    protected int attackCnt =0;
    protected int ytmp=y;
    protected int xtmp=x;
    protected static int jumpCnt=0;
    protected static boolean jumpAvailable=true;
    protected static boolean endAttack =true;
    protected static boolean oldAtack2=false;

    protected Player(){}

    public boolean getRight(){return right;}

    public static boolean getEndAttackStatus(){return endAttack;}

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public List<ShinobiShuriken> listOfShurikens= new ArrayList<>();

    public int getLifeStatus() {
        return lifeStatus;
    }

    protected void attack() {
        if(KeyboardControl.atack)
            endAttack =false;

        if(endAttack ==false)
        {
            KeyboardControl.atack=false;
            attackCnt++;
        }
        if(attackCnt >60)
        {
            endAttack =true;
            attackCnt =0;
        }
    }

    protected void attackWithShuriken(){
        if(KeyboardControl.atack2 != oldAtack2 && KeyboardControl.atack2) {
            listOfShurikens.add(new ShinobiShuriken(x, y));
            oldAtack2=KeyboardControl.atack2;
        }
        if(!KeyboardControl.atack2){
            oldAtack2= false;
        }
    }

    public void jump(){
        ytmp=y;
        xtmp=x;
        KeyboardControl.jump=false;

        ytmp-=jumpHeight;

        while(checkWallCollision() || ytmp<=32)
        {
            ytmp=ytmp+2;
        }
    }

    public abstract void draw();

    public void update() {
        listOfShurikens.forEach((i)->i.update());
        ytmp=y;

        xtmp=x+ KeyboardControl.velocityX * playerSpeed;

        if(KeyboardControl.velocityX== 1) right=true;

        if(KeyboardControl.velocityX==-1) right=false;

        attack();
        attackWithShuriken();

        if(KeyboardControl.jump && jumpAvailable) {
            jump();
            jumpAvailable=false;
        }
        if(jumpCnt>30){
            jumpAvailable=true;
            jumpCnt=0;
        }

        if(!checkWallCollision())
        {
            x=xtmp;
            y=ytmp;
        }

        if(!jumpAvailable){
            jumpCnt++;
            ytmp+=5;
        }
        else{
            ytmp+=3;
        }
       if(!checkWallCollision())
        {
            x=xtmp;
            y=ytmp;
        }

        if(checkNextLevelCollision())
        {
            Map.index++;
        }
        //System.out.println(x+"\t"+y);
    }

    public void increaseLifeStatus()
    {
        if(lifeStatus<100)
        {
            if(lifeStatus+30>100){
                lifeStatus=100;
            }else{
                lifeStatus+=30;
            }
        }
    }

    public void takeDamage(int damage){
        lifeStatus-=damage;
        GameWindow.getDamage(lifeStatus);
    }

    public boolean checkWallCollision(){
        return Collision.checkCollisions(xtmp,ytmp,
                playerWidth * playerDrawScale -10,
                playerHeight * playerDrawScale -10,
                wallCollisions);
    }

    public boolean checkNextLevelCollision(){
        return Collision.checkCollisions(x,y,
                playerWidth,
                playerHeight,
                nextLevelCollision );
    }

}
