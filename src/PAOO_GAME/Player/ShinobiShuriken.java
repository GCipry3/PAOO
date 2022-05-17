package PAOO_GAME.Player;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Drawable;
import PAOO_GAME.Graphics.Assets;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;

public class ShinobiShuriken implements Drawable {
    private int x;
    private int y;
    private int direction; //0 reprezinta stanga, iar 1 dreapta
    private int speed=4;
    private int projectileWidth=24;
    private int projectileHeight=24;
    private boolean visible=true;

    ShinobiShuriken(int x,int y){
        this.x=x;
        this.y=y;
        direction = player.getRight()? 1:0;

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
        if(visible) {
            Drawer.draw(x, y, Assets.gold, projectileWidth, projectileHeight);
            //System.out.println("projectile");
        }
    }

    @Override
    public void update() {
        if(visible) {
            move();

            if(Collision.checkCollisions(x,y,projectileWidth,projectileHeight,
                    wallCollisions)){
                visible = false;
                System.out.println("wall touched by projectile");
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
