package PAOO_GAME.Player;

import PAOO_GAME.Component.Drawer;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Graphics.Assets.*;

public class BlackPlayer extends Player {
    private static int cntDraw=0;
    public BlackPlayer()
    {
        super();
        lifeStatus=750;
    }

    @Override
    public void draw(){
        {
            if(right)
            {
                if(cntDraw<20) {
                    Drawer.draw(
                            x, y,
                            blackNinjaRight,
                            playerWidth,
                            playerHeight
                    );
                }else if(cntDraw<40){
                    Drawer.draw(
                            x, y,
                            blackNinjaRight1,
                            playerWidth,
                            playerHeight
                    );
                }else{
                    Drawer.draw(
                            x, y,
                            blackNinjaRight1,
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
                            blackNinjaLeft,
                            playerWidth,
                            playerHeight
                    );
                }else if(cntDraw<40){
                    Drawer.draw(
                            x, y,
                            blackNinjaLeft1,
                            playerWidth,
                            playerHeight
                    );
                }else{

                    Drawer.draw(
                            x, y,
                            blackNinjaLeft1,
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


}
