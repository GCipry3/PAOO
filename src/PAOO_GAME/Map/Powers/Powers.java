package PAOO_GAME.Map.Powers;

import PAOO_GAME.Drawable;
import PAOO_GAME.Game;

import static PAOO_GAME.Collisions.Collision.checkCollision;
import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.*;

public abstract class Powers implements Drawable {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean visible=true;


    public Powers(int _x,int _y, int _width, int _height) {
        x=_x;
        y=_y;
        width=_width;
        height=_height;
    }

    protected abstract void power();

    public void update() {
        power();
    }

    public abstract void draw();

    protected boolean collectFromAttackCheck(){
        return player.getAttackStatus() &&
                checkCollision(
                        player.getX() -32,
                        player.getY() -32,
                        shurikenCirclePixels,
                        shurikenCirclePixels,
                        x,y, width, height);
    }

    protected boolean collectFromCollisionCheck(){
        return checkCollision(
                player.getX(),
                player.getY(),
                playerWidth,
                playerHeight,
                x,y, width, height);
    }

    protected void increasePlayerHeight(int x){
        jumpHeight+=x;
    }

    protected void gameSetGoldCollectedFlag(){
        player.setGoldCollected();
    }

    protected void gameWindowsAddShurikensToCounter(int x){
        Game.getInstance().setGameWindowShurikenCounter(Game.getInstance().getGameWindowShurikenCounter()+x);
    }

    protected void playerIncreaseCoinsCollected(){
        player.increaseCoins();
    }
}
