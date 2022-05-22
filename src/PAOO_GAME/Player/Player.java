package PAOO_GAME.Player;

import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Drawable;
import PAOO_GAME.Game;
import PAOO_GAME.GameWindow.GameWindow;

import java.util.ArrayList;
import java.util.List;

import static PAOO_GAME.Collisions.Collision.checkCollisions;
import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.*;
import static PAOO_GAME.GameWindow.GameWindow.setCoins;
import static PAOO_GAME.Map.Map.compareIndexWithNrMaps;
import static PAOO_GAME.Map.Map.increaseIndexOfMap;

public abstract class Player implements  Drawable {

    protected int lifeStatus;

    protected static int x;
    protected static int y;

    protected boolean right;

    private int attackCnt =0;
    private int yTmp =y;
    private int xTmp =x;

    private int jumpCnt=0;
    private boolean jumpAvailable=true;

    private boolean endAttack  =true;
    private boolean oldAttack2 =false;

    private boolean goldCollected=false;

    protected int coins=Database.getInstance().get("Coins");
    private final List<ShinobiShuriken> listOfShurikens= new ArrayList<>();

    protected Player(){
        x=tileWidth+5;
        y=20*tileHeight-5;
        lifeStatus=500;
    }

    public void drawShurikens(){
        listOfShurikens.forEach(ShinobiShuriken::draw);
    }

    public List<ShinobiShuriken> getListOfShurikens(){return listOfShurikens;}

    public void setGoldCollected(){goldCollected=true;}

    public boolean getRight(){return right;}

    public boolean getAttackStatus(){return !endAttack;}

    public void setEndAttack(boolean value){endAttack=value;}

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int _x){x=_x;}
    public void setY(int _y){y=_y;}
    public void increaseCoins(){
        coins++;
        setCoins(coins);
        Database.getInstance().update("Coins",coins);
    }
    public int getCoins(){return coins;}
    public int getLifeStatus(){return lifeStatus;}

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
        int shurikenCounter= Game.getInstance().getGameWindowShurikenCounter();
        if(shurikenCounter>0) {
            if (KeyboardControl.attack2 != oldAttack2 && KeyboardControl.attack2) {
                listOfShurikens.add(new ShinobiShuriken(x, y,right? 1:0));
                if (goldCollected) {
                    listOfShurikens.add(new ShinobiShuriken(x - 8, y + 20,right? 1:0));
                    listOfShurikens.add(new ShinobiShuriken(x - 16, y - 20,right? 1:0));
                }
                oldAttack2 = KeyboardControl.attack2;
                Game.getInstance().setGameWindowShurikenCounter(shurikenCounter-1);
            }
            if (!KeyboardControl.attack2) {
                oldAttack2 = false;
            }
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
        yTmp +=1; //gravity

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
        this.attack();
        this.attackWithShuriken();
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
            if(!checkUpperWallCollisionWithTmpPosition()) {
                jump(); //jump 30 times only if there were no collisions with walls
            }
            else{
                jumpAvailable=true;
                jumpCnt=0;
            }
        }
        else{
            yTmp +=2; //gravity
        }
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
            increaseIndexOfMap();
            if(compareIndexWithNrMaps()){
                setWinFlag();
            }
        }
        //endOfCheckNextLevel

        if(lifeStatus<=0){
            setLoseFlag();
        }
    }

    public void takeDamage(int damage){
        lifeStatus-=damage;
        GameWindow.setLifeBarStatus(lifeStatus);
    }

    public boolean checkWallCollisionWithTmpPosition(){
        return checkCollisions(
                xTmp, yTmp,
                playerWidth-10,
                playerHeight-10,
                wallCollisions
        );
    }
    public boolean checkUpperWallCollisionWithTmpPosition(){
        return checkCollisions(
                xTmp, yTmp-5,
                playerWidth-10,
                playerHeight-10,
                wallCollisions
        );
    }

    public boolean checkNextLevelCollision(){
        return checkCollisions(
                x,y,
                playerWidth,
                playerHeight,
                nextLevelCollision
        );
    }

}
