package PAOO_GAME.Player;

import PAOO_GAME.Component.Coord;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
    public static Coord velocity=new Coord(0,0);
    public static boolean atack=false;

    public static char ch;
    @Override
    public void keyTyped(KeyEvent e) {/*System.out.println("Let's GOOO");*/}
    @Override
    public void keyPressed(KeyEvent e)
    {

        int key=e.getKeyCode();

        if(key == KeyEvent.VK_W)
        {
            velocity.setY(-1);
        }
        if(key == KeyEvent.VK_S)
        {
            velocity.setY(1);
        }
        if(key == KeyEvent.VK_A)
        {
            velocity.setX(-1);
        }
        if(key == KeyEvent.VK_D)
        {
            velocity.setX(1);
        }
        if(key == KeyEvent.VK_E)
        {
            atack=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key=e.getKeyCode();
        if(key == KeyEvent.VK_W)
        {
            velocity.setY(0);
        }
        if(key == KeyEvent.VK_S)
        {
            velocity.setY(0);
        }
        if(key == KeyEvent.VK_A)
        {
            velocity.setX(0);
        }
        if(key == KeyEvent.VK_D)
        {
            velocity.setX(0);
        }
    }
}
