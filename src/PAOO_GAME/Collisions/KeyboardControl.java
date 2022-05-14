package PAOO_GAME.Collisions;

import PAOO_GAME.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
    public static int velocityX=0;
    public static int velocityY=0;

    public static boolean atack=false;

    public static boolean jump=false;

    @Override
    public void keyTyped(KeyEvent e) {/*System.out.println("Let's GOOO");*/}
    @Override
    public void keyPressed(KeyEvent e)
    {

        int key=e.getKeyCode();

        if(key == KeyEvent.VK_W)
        {
            velocityY=-1;
            jump=true;
        }
        if(key == KeyEvent.VK_S)
        {
            velocityY=1;
        }
        if(key == KeyEvent.VK_A)
        {
            velocityX=-1;
        }
        if(key == KeyEvent.VK_D)
        {
            velocityX=1;
        }
        if(key == KeyEvent.VK_E)
        {
            atack=true;
        }
        if(key == KeyEvent.VK_ESCAPE){
            Game gameInstance=Game.getInstance();
            gameInstance.StopGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key=e.getKeyCode();
        if(key == KeyEvent.VK_W)
        {
            //KeyboardControl.jump=false;
            velocityY=0;
        }
        if(key == KeyEvent.VK_S)
        {
            velocityY=0;
        }
        if(key == KeyEvent.VK_A)
        {
            velocityX=0;
        }
        if(key == KeyEvent.VK_D)
        {
            velocityX=0;
        }
    }
}
