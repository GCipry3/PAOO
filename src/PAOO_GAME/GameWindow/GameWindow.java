package PAOO_GAME.GameWindow;

import PAOO_GAME.Collisions.KeyboardControl;

import javax.swing.*;
import java.awt.*;

public final class GameWindow{
    private JFrame wndFrame;
    private static JProgressBar healthBar;
    private final String wndTitle;
    private static JTextField textField;
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

        textField =new JTextField();
        textField.setVisible(true);
        textField.setBounds(wndWidth-100,100,48,48);
        textField.setText("0");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEditable(false);
        textField.setBackground(new Color(255, 152, 20));
        Font font=new Font(textField.getFont().getName(),textField.getFont().getStyle(),32);
        textField.setFont(font);

        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(wndWidth,wndHeight));
        canvas.setMaximumSize(new Dimension(wndWidth,wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth,wndHeight));
        canvas.addKeyListener(new KeyboardControl());

        wndFrame.add(textField);
        wndFrame.add(healthBarPanel);
        wndFrame.add(canvas);

        wndFrame.pack();

    }

    public static void getDamage(int health){
        healthBar.setValue(health);
    }

    public static void setCoins(int coins){
        textField.setText(String.valueOf(coins));
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