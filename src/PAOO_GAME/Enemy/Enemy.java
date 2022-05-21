package PAOO_GAME.Enemy;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Drawable;

import java.util.ArrayList;
import java.util.List;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;

public abstract class Enemy implements  Drawable {
    protected int x;
    protected int y;
    protected int firstX;//the enemy will go left and right
    protected int firstY;//initial positions
    protected boolean visible=true;
    protected int state=0; //0 means go left, 1 means go right
    protected int damage;

    protected List<Projectile> listOfProjectiles= new ArrayList<>();

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

    protected void giveDamageToPlayer(int x){
        player.takeDamage(x);
    }

    public void drawProjectiles()  {listOfProjectiles.forEach(Projectile::draw);  }
    public void updateProjectiles(){listOfProjectiles.forEach(Projectile::update);}
    public void attackProjectiles(){listOfProjectiles.forEach(Projectile::attack);}

    protected void createNewProjectile(){
        listOfProjectiles.add(new Projectile(x,y+40,damage));
    }

    protected void moveLeftState(){
        x-=3;
    }
    protected void moveRightState(){
        x+=3;
    }

    public boolean playerClose(){
        int xPlayer= player.getX();
        int yPlayer= player.getY();

        return (x - xPlayer < 10 * tileWidth) &&
                (x - xPlayer > -10 * tileWidth) &&
                (y - yPlayer < 3 * tileHeight) &&
                (y - yPlayer > -3 * tileHeight);
    }

    public boolean collisionWithShurikensCheck(){
        for(int i=0;i<player.getListOfShurikens().size();i++){
            player.getListOfShurikens().get(i);
            if(player.getListOfShurikens().get(i).getVisible()){
                if(Collision.checkCollision(
                        x,y,enemyWidth,enemyHeight,
                        player.getListOfShurikens().get(i).getX(),
                        player.getListOfShurikens().get(i).getY(),
                        player.getListOfShurikens().get(i).getProjectileWidth(),
                        player.getListOfShurikens().get(i).getProjectileHeight()
                ))
                {
                    player.getListOfShurikens().get(i).setVisibleFalse();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collisionWithAttackStateCheck(){
        int xPlayer= player.getX();
        int yPlayer= player.getY();

        return Collision.checkCollision(
                x,y,
                enemyWidth,enemyHeight,
                xPlayer-32,yPlayer-32,
                shurikenCirclePixels,
                shurikenCirclePixels
        );
    }

    public boolean playerAttackStatusCheck(){
        return player.getAttackStatus();
    }

    public boolean collisionWithPlayerCheck(){
        int xPlayer= player.getX();
        int yPlayer= player.getY();

        return Collision.checkCollision(
                x,
                y,
                enemyWidth,
                enemyHeight,
                xPlayer,
                yPlayer,
                playerWidth,
                playerHeight);
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
}
