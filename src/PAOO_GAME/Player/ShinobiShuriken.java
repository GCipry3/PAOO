package PAOO_GAME.Player;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Drawable;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;

public class ShinobiShuriken implements Drawable {
    private int x;
    private final int y;
    private final int direction; //0 means left, and 1 means right
    private final int projectileWidth=32;
    private final int projectileHeight=32;
    private int cntDraw=0;
    private boolean visible=true;

    ShinobiShuriken(int x,int y){
        this.x=x;
        this.y=y;
        direction = player.getRight()? 1:0;

    }

    public void move(){
        int speed = 4;
        switch (direction) {
            case 0 -> x -= speed;
            case 1 -> x += speed;
        }

    }

    @Override
    public void draw() {
        int delay=2;
        if(visible) {
            cntDraw++;
            if(cntDraw<delay) {
                Drawer.draw(x, y, Assets.shuriken.get(0), projectileWidth, projectileHeight);
            }else if(cntDraw<2*delay){
                Drawer.draw(x, y, Assets.shuriken.get(1), projectileWidth, projectileHeight);
            }else if(cntDraw<3*delay){
                Drawer.draw(x, y, Assets.shuriken.get(2), projectileWidth, projectileHeight);
            }else if(cntDraw<4*delay){
                Drawer.draw(x, y, Assets.shuriken.get(3), projectileWidth, projectileHeight);
            }else{
                cntDraw=0;
                Drawer.draw(x, y, Assets.shuriken.get(0), projectileWidth, projectileHeight);
            }
        }
    }

    @Override
    public void update() {
        if(visible) {
            move();

            if(Collision.checkCollisions(x,y,projectileWidth,projectileHeight,
                    wallCollisions)){
                visible = false;
                //System.out.println("wall touched by projectile");
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
    public void setVisibleFalse(){visible=false;}
}
