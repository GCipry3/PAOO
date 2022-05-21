package PAOO_GAME.Enemy;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Drawable;

import static PAOO_GAME.Collisions.Collision.checkCollision;
import static PAOO_GAME.Collisions.Collision.checkCollisions;
import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;
import static PAOO_GAME.Graphics.Assets.rock;

public class Projectile implements Drawable {
    private int x;
    private final int y;
    private final int direction; //0 means left, and 1 right
    private final int projectileWidth=16;
    private final int projectileHeight=16;
    private boolean visible=true;
    private final int damage;

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
        int speed = 2;
        switch (direction) {
            case 0 -> x -= speed;
            case 1 -> x += speed;
        }

    }

    @Override
    public void draw() {
        if(visible) {
            Drawer.draw(x, y, rock, projectileWidth, projectileHeight);
        }
    }

    @Override
    public void update() {
        if(visible) {
            move();

            if(checkCollisions(x,y,projectileWidth,projectileHeight,wallCollisions)){
                visible = false;
            }
        }
    }

    public void attack(){
        if(visible) {
            int xPlayer = player.getX();
            int yPlayer = player.getY();

            if (checkCollision(x, y, projectileWidth, projectileHeight,
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
}
