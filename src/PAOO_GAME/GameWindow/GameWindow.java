package PAOO_GAME.GameWindow;

import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Collisions.MouseControl;

import javax.swing.*;
import java.awt.*;

public final class GameWindow{
    private JFrame wndFrame;
    private static JPanel healthBarPanel;
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

        healthBarPanel = new JPanel();
        healthBarPanel.setVisible(false);
        healthBarPanel.setBounds(wndWidth-300,48,250,30);
        healthBarPanel.setBackground(new Color(14, 24, 37));

        healthBar= new JProgressBar(0, 750);
        healthBar.setPreferredSize(new Dimension(250,30));
        healthBar.setForeground(new Color(183, 11, 11));
        healthBar.setVisible(true);

        healthBarPanel.add(healthBar);

        shurikenCounter =new JTextField();
        shurikenCounter.setVisible(false);
        shurikenCounter.setBounds(wndWidth-160,100,48,48);
        shurikenCounter.setText("0");
        shurikenCounter.setHorizontalAlignment(SwingConstants.CENTER);
        shurikenCounter.setEditable(false);
        shurikenCounter.setBackground(new Color(0, 120, 255));
        Font font=new Font(shurikenCounter.getFont().getName(), shurikenCounter.getFont().getStyle(),32);
        shurikenCounter.setFont(font);

        coinsCounter =new JTextField();
        coinsCounter.setVisible(false);
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
        canvas.addMouseListener(MouseControl.getInstance() );

        wndFrame.add(shurikenCounter);
        wndFrame.add(coinsCounter);
        wndFrame.add(healthBarPanel);
        wndFrame.add(canvas);

        wndFrame.pack();

    }

    public static void setLifeBarStatus(int health){
        healthBar.setValue(health);
    }

    public static void setAllToVisible(){
        healthBarPanel.setVisible(true);
        shurikenCounter.setVisible(true);
        coinsCounter.setVisible(true);
    }
    public static void setAllToNotVisible(){
        healthBarPanel.setVisible(false);
        shurikenCounter.setVisible(false);
        coinsCounter.setVisible(false);
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