package PAOO_GAME.Player;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Map.Map;

import java.awt.image.BufferedImage;

import static PAOO_GAME.Constants.*;

public abstract class Player {

    protected String name;
    protected int lives;

    protected static int x;
    protected static int y;


    protected boolean right=false;
    protected int atackCnt=0;
    protected int ytmp=y;
    protected int xtmp=x;
    protected static int jumpCnt=0;
    protected static boolean jumpAvailable=true;
    protected static boolean endAtack=true;

    protected BufferedImage textureLeft;
    protected BufferedImage textureRight;


    /*public abstract String getName();
    public abstract int getLives();
    public abstract void Init();
    public abstract void atack();
    public abstract void draw();
    public abstract void update();
    public abstract boolean increaseLife();*/

    protected Player(){}

    public static boolean getEndAtackStatus(){return endAtack;}
    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public void Init() {
        lives =3;
    }

    protected void atack() {
        if(KeyboardControl.atack)
            endAtack=false;

        if(endAtack==false)
        {
            KeyboardControl.atack=false;
            atackCnt++;
        }
        if(atackCnt>60)
        {
            endAtack=true;
            atackCnt=0;
        }
    }

    public void jump(){
        ytmp=y;
        xtmp=x;
        KeyboardControl.jump=false;

        ytmp-=70;

        while(checkWallCollision())
        {
            ytmp=ytmp+2;
        }
    }

    public abstract void draw();

    public void update() {
        ytmp=y;

        xtmp=x+ KeyboardControl.velocityX * playerSpeed;

        if(KeyboardControl.velocityX== 1) right=true;

        if(KeyboardControl.velocityX==-1) right=false;

        /*if(jumps<jumpTimes && ok)
        {
            //tmp.setY(pos.getY()+KeyboardControl.velocity.getY()*speed*jumpScale);
            ytmp=y+KeyboardControl.velocityY*speed*jumpScale;
            if(KeyboardControl.velocityY == -1)
            {
                jumps++;
                okFall=false;
                //System.out.println("Print Jumps: "+jumps);
            }
        }
        else
        {
            //tmp.setY(pos.getY());
            ytmp=y;
            jumps=0;
            ok=false;
        }

        if(!ok)
        {
            //tmp.setY(tmp.getY()+1);
            ytmp=ytmp+1;
            if(Collision.checkAllWallCollisions(xtmp,ytmp,width*scale-10,height*scale-10))
            {
               ok=true;
               okFall=true;
            }
        }
        */

        atack();
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
            ytmp+=1;
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

    public boolean increaseLife()
    {
        if(lives<3)
        {
            lives++;
            return true;
        }
        return false;
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
