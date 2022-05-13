package PAOO_GAME.Player;

import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Map.Map;
import PAOO_GAME.Collisions.Collision;

import java.awt.image.BufferedImage;

public abstract class Player {

    protected String name;
    protected int lives;

    public int x;
    public int y;

    protected int speed=3;
    protected int width=32;
    protected int height=32;
    protected int scale= 2;

    protected boolean right=false;
    protected int atackCnt=0;
    public static boolean endAtack=true;
    protected int ytmp=y;
    protected int xtmp=x;
    public static int shurikenCircle =128;
    protected static boolean jumpAvailable=true;
    protected static int jumpCnt=0;

    protected BufferedImage textureLeft;
    protected BufferedImage textureRight;


    /*public abstract String getName();
    public abstract int getLives();
    public abstract void Init();
    public abstract void atack();
    public abstract void draw();
    public abstract void update();
    public abstract boolean increaseLife();*/


    public int getWidth(){return width;}
    public int getHeight(){return height;}

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public void Init() {
        lives =3;
    }

    public void atack() {
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

        while(Collision.checkAllWallCollisions(xtmp,ytmp,width*scale-10,height*scale-10)){
            ytmp=ytmp+2;
        }
    }

    public abstract void draw();

    public void update() {
        ytmp=y;

        xtmp=x+ KeyboardControl.velocityX*speed;

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
        if(!jumpAvailable){
            jumpCnt++;
        }
        if(jumpCnt>30){
            jumpAvailable=true;
            jumpCnt=0;
        }

        if(!Collision.checkAllWallCollisions(xtmp,ytmp,width*scale-10,height*scale-10))
        {
            x=xtmp;
            y=ytmp;
        }
        ytmp+=2;

        if(KeyboardControl.velocityY== 1) {ytmp+=5;}
        if(!Collision.checkAllWallCollisions(xtmp,ytmp,width*scale-10,height*scale-10))
        {
            x=xtmp;
            y=ytmp;
        }

        if(Collision.checkNextLevel(x,y,this.getWidth(),this.getHeight()))
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
}
