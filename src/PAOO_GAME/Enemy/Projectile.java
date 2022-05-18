package PAOO_GAME.Enemy;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Drawable;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;

public class Projectile implements Drawable {
    private int x;
    private int y;
    private int direction; //0 reprezinta stanga, iar 1 dreapta
    private int speed=2;
    private int projectileWidth=16;
    private int projectileHeight=16;
    private boolean visible=true;
    private int damage;

    Projectile(int x,int y,int damage){
        int xPlayer= player.getX();
        this.x=x;
        this.y=y;
        if (xPlayer<x){
            direction=0;
        }else{
            direction=1;
        }

        this.damage=damage;
    }

    public void move(){
        switch (direction){
            case 0:
                x-=speed;
                break;
            case 1:
                x+=speed;
                break;
        }

    }

    @Override
    public void draw() {
        if(visible==true) {
            Drawer.draw(x, y, Assets.rock, projectileWidth, projectileHeight);
            //System.out.println("projectile");
        }
    }

    @Override
    public void update() {
        if(visible==true) {
            move();

            if(Collision.checkCollisions(x,y,projectileWidth,projectileHeight,
                    wallCollisions)){
                visible = false;
            }
        }
    }

    public void attack(){
        if(visible) {
            int xPlayer = player.getX();
            int yPlayer = player.getY();

            if (Collision.checkCollision(x, y, projectileWidth, projectileHeight,
                    xPlayer, yPlayer + 5, playerWidth, playerHeight)) {
                player.takeDamage(damage);
                visible = false;
            }
        }
    }

    public boolean getVisible(){
        return visible;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getProjectileWidth(){return projectileWidth;}
    public int getProjectileHeight(){return projectileHeight;}
}
