package PAOO_GAME.Collisions;

import PAOO_GAME.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
    public static int velocityX=0;
    public static int velocityY=0;

    public static boolean attack =false;
    public static boolean attack2 =false;


    public static boolean jump=false;

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e)
    {

        int key=e.getKeyCode();

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_SPACE)
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
            attack =true;
        }
        if(key == KeyEvent.VK_Q){
            attack2 =true;
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
        if(key == KeyEvent.VK_Q){
            attack2 =false;
        }

    }
}
