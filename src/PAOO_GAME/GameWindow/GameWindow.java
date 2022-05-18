package PAOO_GAME.GameWindow;

import PAOO_GAME.Collisions.KeyboardControl;

import javax.swing.*;
import java.awt.*;

public final class GameWindow{
    private JFrame wndFrame;
    private static JProgressBar healthBar;
    private final String wndTitle;
    private static JTextField coinsCounter;
    private static JTextField shurikenCounter;//at each diamond collected you get 5 shuriken shoots
    private final int wndWidth;
    private final int wndHeight;

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

        JPanel healthBarPanel = new JPanel();
        healthBarPanel.setBounds(wndWidth-300,48,250,30);
        healthBarPanel.setBackground(new Color(14, 24, 37));

        healthBar= new JProgressBar(0,500);
        healthBar.setPreferredSize(new Dimension(250,30));
        healthBar.setForeground(new Color(183, 11, 11));
        healthBar.setVisible(true);
        healthBar.setValue(500);

        healthBarPanel.add(healthBar);

        shurikenCounter =new JTextField();
        shurikenCounter.setVisible(true);
        shurikenCounter.setBounds(wndWidth-160,100,48,48);
        shurikenCounter.setText("0");
        shurikenCounter.setHorizontalAlignment(SwingConstants.CENTER);
        shurikenCounter.setEditable(false);
        shurikenCounter.setBackground(new Color(0, 120, 255));
        Font font=new Font(shurikenCounter.getFont().getName(), shurikenCounter.getFont().getStyle(),32);
        shurikenCounter.setFont(font);

        coinsCounter =new JTextField();
        coinsCounter.setVisible(true);
        coinsCounter.setBounds(wndWidth-100,100,48,48);
        coinsCounter.setText("0");
        coinsCounter.setHorizontalAlignment(SwingConstants.CENTER);
        coinsCounter.setEditable(false);
        coinsCounter.setBackground(new Color(255, 152, 20));
        font=new Font(coinsCounter.getFont().getName(), coinsCounter.getFont().getStyle(),32);
        coinsCounter.setFont(font);

        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(wndWidth,wndHeight));
        canvas.setMaximumSize(new Dimension(wndWidth,wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth,wndHeight));
        canvas.addKeyListener(new KeyboardControl());

        wndFrame.add(shurikenCounter);
        wndFrame.add(coinsCounter);
        wndFrame.add(healthBarPanel);
        wndFrame.add(canvas);

        wndFrame.pack();

    }

    public static void getDamage(int health){
        healthBar.setValue(health);
    }

    public static void setCoins(int coins){
        coinsCounter.setText(String.valueOf(coins));
    }

    public static void setShurikenCounter(int shurikenNumber){shurikenCounter.setText(Integer.toString(shurikenNumber));}

    public static int getShurikenCounter(){return Integer.parseInt(shurikenCounter.getText());}

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