package PAOO_GAME.GameWindow;

import PAOO_GAME.Collisions.KeyboardControl;

import javax.swing.*;
import java.awt.*;

public final class GameWindow{
    private JFrame wndFrame;
    private JPanel healthBarPanel;
    private static JProgressBar healthBar;
    private String wndTitle;
    // private JTextField textField=new JTextField();
    private int wndWidth;
    private int wndHeight;

    private Canvas canvas;


    public GameWindow(String title,int width,int height)
    {
        wndTitle=title;
        wndWidth=width;
        wndHeight=height;
        wndFrame=null;
    }

    public void BuildGameWindow()
    {
        if(wndFrame != null)
        {
            return;
        }

        wndFrame=new JFrame(wndTitle);
        wndFrame.setSize(wndWidth,wndHeight);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);

        wndFrame.setVisible(true);
        wndFrame.setFocusable(true);

        healthBarPanel= new JPanel();
        healthBarPanel.setBounds(wndWidth-350,48,300,30);
        healthBarPanel.setBackground(Color.red);

        healthBar= new JProgressBar(1,100);
        healthBar.setPreferredSize(new Dimension(300,30));
        healthBar.setValue(100);

        healthBarPanel.add(healthBar);

        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(wndWidth,wndHeight));
        canvas.setMaximumSize(new Dimension(wndWidth,wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth,wndHeight));
        canvas.addKeyListener(new KeyboardControl());

        wndFrame.add(healthBarPanel);
        wndFrame.add(canvas);

        wndFrame.pack();
    }

    public static void getDamage(int health){
        healthBar.setValue(health);
    }

    public int GetWndWidth()
    {
        return wndWidth;
    }

    public int GetWndHeight()
    {
        return wndHeight;
    }

    public Canvas GetCanvas()
    {
        return canvas;
    }


}