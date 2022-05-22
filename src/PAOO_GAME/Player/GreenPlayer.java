package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Graphics.Assets.*;

public class GreenPlayer extends Player {
    private static int cntDraw=0;
    public GreenPlayer()
    {
        super();
        lifeStatus=500;
    }

    @Override
    public void draw(){
        if(right)
        {
            if(cntDraw<20) {
                Drawer.draw(
                        x, y,
                        greenNinjaRight,
                        playerWidth,
                        playerHeight
                );
            }
            else if(cntDraw<40){
                Drawer.draw(
                        x, y,
                        greenNinjaRight1,
                        playerWidth,
                        playerHeight
                );
            }else{
                Drawer.draw(
                        x, y,
                        greenNinjaRight1,
                        playerWidth,
                        playerHeight
                );
                cntDraw=0;
            }
        }
        else
        {
            if(cntDraw<20) {
                Drawer.draw(
                        x, y,
                        greenNinjaLeft,
                        playerWidth,
                        playerHeight
                );
            }else if(cntDraw<40){
                Drawer.draw(
                        x, y,
                        greenNinjaLeft1,
                        playerWidth,
                        playerHeight
                );
            }else{
                Drawer.draw(
                        x, y,
                        greenNinjaLeft1,
                        playerWidth,
                        playerHeight
                );
                cntDraw=0;
            }
        }

        if(getAttackStatus())
        {
            Drawer.draw(
                    x-32,y-32,
                    ShurikenBg,
                    shurikenCirclePixels,
                    shurikenCirclePixels
            );
        }

        drawShurikens();
        cntDraw++;

    }
}
