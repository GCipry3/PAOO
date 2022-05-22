package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Graphics.Assets.*;

public class BluePlayer extends Player{
    private static int cntDraw=0;
    public BluePlayer()
    {
        super();
        lifeStatus=250;
    }

    @Override
    public void draw(){
        {
            if(right)
            {
                if(cntDraw<20) {
                    Drawer.draw(
                            x, y,
                            blueNinjaRight,
                            playerWidth,
                            playerHeight
                    );
                }
                else if(cntDraw<40){
                    Drawer.draw(
                            x, y,
                            blueNinjaRight1,
                            playerWidth,
                            playerHeight
                    );
                }else{
                    Drawer.draw(
                            x, y,
                            blueNinjaRight1,
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
                            blueNinjaLeft,
                            playerWidth,
                            playerHeight
                    );
                }

                else if(cntDraw<40){
                    Drawer.draw(
                            x, y,
                            blueNinjaLeft1,
                            playerWidth,
                            playerHeight
                    );
                }else{
                    Drawer.draw(
                            x, y,
                            blueNinjaLeft1,
                            playerWidth,
                            playerHeight
                    );
                    cntDraw=0;
                }
            }
            cntDraw++;

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
        }
    }
}
