package PAOO_GAME.Collisions;

import PAOO_GAME.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
    public static int velocityX=0;
    public static int velocityY=0;

    public static boolean attack =false;
    public static boolean attack2 =false;

    public static boolean w=false;
    public static boolean a=false;
    public static boolean s=false;
    public static boolean d=false;
    public static boolean q=false;
    public static boolean e=false;


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
            w=true;
        }
        if(key == KeyEvent.VK_S)
        {
            velocityY=1;
            s=true;
        }
        if(key == KeyEvent.VK_A)
        {
            velocityX=-1;
            a=true;
        }
        if(key == KeyEvent.VK_D)
        {
            velocityX=1;
            d=true;
        }
        if(key == KeyEvent.VK_E)
        {
            attack =true;
            KeyboardControl.e =true;
        }
        if(key == KeyEvent.VK_Q){
            attack2 =true;
            q=true;
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
            w=false;
            velocityY=0;
        }
        if(key == KeyEvent.VK_S)
        {
            s=false;
            velocityY=0;
        }
        if(key == KeyEvent.VK_A)
        {
            a=false;
            velocityX=0;
        }
        if(key == KeyEvent.VK_D)
        {
            d=false;
            velocityX=0;
        }
        if(key == KeyEvent.VK_Q){
            q=false;
            attack2 =false;
        }
        if(key == KeyEvent.VK_E){
            KeyboardControl.e =false;
        }

    }
}
