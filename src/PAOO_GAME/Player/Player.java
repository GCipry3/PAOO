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

    protected int lifeStatus=500;

    protected static int x;
    protected static int y;

    protected boolean right;

    private int attackCnt =0;
    private int yTmp =y;
    private int xTmp =x;

    private int jumpCnt=0;
    private boolean jumpAvailable=true;

    private static boolean endAttack  =true;
    private static boolean oldAttack2 =false;



    protected int coins=0;
    public List<ShinobiShuriken> listOfShurikens= new ArrayList<>();

    protected Player(){
        x=tileWidth;
        y=20*tileHeight;
    }

    public boolean getRight(){return right;}

    public static boolean getEndAttack(){return !endAttack;}

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int _x){x=_x;}
    public void setY(int _y){y=_y;}
    public void increaseCoins(){
        coins++;
        GameWindow.setCoins(coins);
    }

    protected void attack() {
        if(KeyboardControl.attack)
            endAttack =false;

        if(!endAttack)
        {
            KeyboardControl.attack =false;
            attackCnt++;
        }
        if(attackCnt >60)
        {
            endAttack =true;
            attackCnt =0;
        }
    }

    protected void attackWithShuriken(){
        if(KeyboardControl.attack2 != oldAttack2 && KeyboardControl.attack2) {
            listOfShurikens.add(new ShinobiShuriken(x, y));
            listOfShurikens.add(new ShinobiShuriken(x-16, y+20));
            oldAttack2 =KeyboardControl.attack2;
        }
        if(!KeyboardControl.attack2){
            oldAttack2 = false;
        }
    }

    public void jump(){
        yTmp =y;
        xTmp =x;
        KeyboardControl.jump=false;

        yTmp -=jumpHeight/20;//to make the jump more smooth

        while(checkWallCollisionWithTmpPosition() || yTmp <=32)
        {
            yTmp = yTmp +2;
        }

        boolean wallAboveFound=false;
        int aux= yTmp;
        while(yTmp <y-10){
            if(checkWallCollisionWithTmpPosition()){
                wallAboveFound=true;
                break;
            }
            yTmp +=2;
        }
        if(wallAboveFound){
            while(wallAboveFound){
                if(!checkWallCollisionWithTmpPosition()){
                    wallAboveFound=false;
                }
                yTmp +=2;
            }
        }else{
            yTmp =aux;
        }
    }

    public abstract void draw();

    public void update() {
        //startOfPlayerProjectileUpdate
        listOfShurikens.forEach(ShinobiShuriken::update);
        //endOfPlayerProjectileUpdate

        //startOfDownMovement
        xTmp =x+ KeyboardControl.velocityX * playerSpeed;
        if(KeyboardControl.velocityY == 1){
            yTmp =y+playerSpeed*3;
        }else{
            yTmp =y; //default value for yTmp
        }
        //endOfDownMovement

        //startOfChoosingPlayerSide
        if(KeyboardControl.velocityX== 1) right=true;
        if(KeyboardControl.velocityX==-1) right=false;
        //endOfChoosingPlayerSide

        //startOfAttack
        attack();
        attackWithShuriken();
        //endOfAttack

        //startOfUpdatePlayerPositionBeforeJump
        if(!checkWallCollisionWithTmpPosition())
        {
            x= xTmp;
            y= yTmp;
        }
        //endOfUpdatePlayerPositionBeforeJump

        //startOfJump
        if(KeyboardControl.jump && jumpAvailable) {
            jumpAvailable=false;
        }
        if(jumpCnt>30){ //count 30 means 1/2 seconds
            jumpAvailable=true;
            jumpCnt=0;
        }
        if(!jumpAvailable){
            jumpCnt++;
            jump(); //jump 30 times
        }
        else{
            yTmp +=2; //gravity
        }
        yTmp +=1; //gravity
        //endOfJump

        //startOfUpdatePlayerPositionAfterJump
        if(!checkWallCollisionWithTmpPosition())
        {
            x= xTmp;
            y= yTmp;
        }
        //endOfUpdatePlayerPositionAfterJump

        //startOfCheckNextLevel
        if(checkNextLevelCollision())
        {
            Map.index++;
        }
        //endOfCheckNextLevel

    }

    public void increaseLifeStatus(int amount)
    {
        if(lifeStatus<500)
        {
            if(lifeStatus+amount>500){
                lifeStatus=500;
            }else{
                lifeStatus+=amount;
            }
        }
    }

    public void takeDamage(int damage){
        lifeStatus-=damage;
        GameWindow.getDamage(lifeStatus);
    }

    public boolean checkWallCollisionWithTmpPosition(){
        return Collision.checkCollisions(
                xTmp, yTmp,
                playerWidth-10,
                playerHeight-10,
                wallCollisions
        );
    }

    public boolean checkNextLevelCollision(){
        return Collision.checkCollisions(
                x,y,
                playerWidth,
                playerHeight,
                nextLevelCollision
        );
    }

}
