package PAOO_GAME.Collisions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class MouseControl implements MouseListener {
    private int x;
    private int y;

    private static MouseControl instance=null;

    private MouseControl(){}

    public static MouseControl getInstance(){
        if(instance == null){
            instance = new MouseControl();
        }
        return instance;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void resetCoords(){
        x=0;
        y=0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
