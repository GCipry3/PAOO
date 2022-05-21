package PAOO_GAME.Collisions;

import PAOO_GAME.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
    public static int velocityX=0;
    public static int velocityY=0;

    public static boolean attack =false;
    public static boolean attack2 =false;

    public static boolean wPressed =false;
    public static boolean aPressed =false;
    public static boolean sPressed =false;
    public static boolean dPressed =false;
    public static boolean qPressed =false;
    public static boolean ePressed =false;


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
            wPressed =true;
        }
        if(key == KeyEvent.VK_S)
        {
            velocityY=1;
            sPressed =true;
        }
        if(key == KeyEvent.VK_A)
        {
            velocityX=-1;
            aPressed =true;
        }
        if(key == KeyEvent.VK_D)
        {
            velocityX=1;
            dPressed =true;
        }
        if(key == KeyEvent.VK_E)
        {
            attack =true;
            KeyboardControl.ePressed =true;
        }
        if(key == KeyEvent.VK_Q){
            attack2 =true;
            qPressed =true;
        }

        if(key == KeyEvent.VK_ESCAPE){
            Game.getInstance().StopGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key=e.getKeyCode();
        if(key == KeyEvent.VK_W)
        {
            //KeyboardControl.jump=false;
            wPressed =false;
            velocityY=0;
        }
        if(key == KeyEvent.VK_S)
        {
            sPressed =false;
            velocityY=0;
        }
        if(key == KeyEvent.VK_A)
        {
            aPressed =false;
            velocityX=0;
        }
        if(key == KeyEvent.VK_D)
        {
            dPressed =false;
            velocityX=0;
        }
        if(key == KeyEvent.VK_Q){
            qPressed =false;
            attack2 =false;
        }
        if(key == KeyEvent.VK_E){
            KeyboardControl.ePressed =false;
        }

    }
}
