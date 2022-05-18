package PAOO_GAME.Enemy;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Drawable;
import PAOO_GAME.Game;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.*;

public class Projectile implements Drawable {
    private int x;
    private final int y;
    private final int direction; //0 means left, and 1 right
    private final int projectileWidth=16;
    private final int projectileHeight=16;
    private boolean visible=true;
    private final int damage;

    Projectile(int x,int y,int damage){
        int xPlayer= Game.getInstance().player.getX();
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
            Drawer.draw(x, y, Assets.rock, projectileWidth, projectileHeight);
        }
    }

    @Override
    public void update() {
        if(visible) {
            move();

            if(Collision.checkCollisions(x,y,projectileWidth,projectileHeight,
                    wallCollisions)){
                visible = false;
            }
        }
    }

    public void attack(){
        if(visible) {
            int xPlayer = Game.getInstance().player.getX();
            int yPlayer = Game.getInstance().player.getY();

            if (Collision.checkCollision(x, y, projectileWidth, projectileHeight,
                    xPlayer, yPlayer + 5, playerWidth, playerHeight)) {
                Game.getInstance().player.takeDamage(damage);
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
