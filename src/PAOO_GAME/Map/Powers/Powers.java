package PAOO_GAME.Map.Powers;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Drawable;
import PAOO_GAME.Game;

import static PAOO_GAME.Constants.*;

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
        return Game.getInstance().getPlayerAttackStatus() &&
                Collision.checkCollision(
                        Game.getInstance().getPlayerX() -32,
                        Game.getInstance().getPlayerY() -32,
                        shurikenCirclePixels,
                        shurikenCirclePixels,
                        x,y, width, height);
    }

    protected boolean collectFromCollisionCheck(){
        return Collision.checkCollision(
                Game.getInstance().getPlayerX(),
                Game.getInstance().getPlayerY(),
                playerWidth,
                playerHeight,
                x,y, width, height);
    }

    protected void increasePlayerHeight(int x){
        jumpHeight+=x;
    }

    protected void gameSetGoldCollectedFlag(){
        Game.getInstance().playerSetGoldCollected();
    }

    protected void gameWindowsAddShurikensToCounter(int x){
        Game.getInstance().setGameWindowShurikenCounter(Game.getInstance().getGameWindowShurikenCounter()+x);
    }

    protected void playerIncreaseCoinsCollected(){
        Game.getInstance().playerIncreaseCoin();
    }
}
