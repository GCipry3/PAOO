package PAOO_GAME;

import PAOO_GAME.GameWindow.GameWindow;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Map.Map;
import PAOO_GAME.Player.BlackPlayer;
import PAOO_GAME.Player.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import static PAOO_GAME.Constants.tileHeight;
import static PAOO_GAME.Constants.tileWidth;

public class Game extends Component implements Runnable {

    private GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    //private Jumper jumper1=new Jumper(new Coord(17* tileHeight,19* tileWidth),64,64);
    //private Jumper jumper2=new Jumper(new Coord(2* tileHeight,10* tileWidth),64,64);

    public static final int widthTiles = 50;
    public static final int heightTiles = 25;

    public static Graphics g;

    public static Player player;
    public Map m=new Map(0);


    public Game()
    {
        runState = false;
        player=new BlackPlayer("Ciprian");
    }

    private void InitGame() throws IOException {
        wnd = new GameWindow("Schelet Proiect PAOO",
                widthTiles*tileWidth,
                heightTiles*tileHeight);

        m.init();
        wnd.BuildGameWindow();

        Assets.Init();
    }

    @Override
    public void run()
    {
        try {
            InitGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long oldTime = System.nanoTime();
        long curentTime;

        final int framesPerSecond   = 60;
        final double timeFrame = 1000000000 / framesPerSecond;

        while (runState == true)
        {
            curentTime = System.nanoTime();
            if((curentTime - oldTime) > timeFrame)
            {
                update();
                draw();
                oldTime = curentTime;
            }
        }

    }

    public synchronized void StartGame()
    {
        if(runState == false)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        else
        {
            return;
        }
    }

    public synchronized void StopGame()
    {
        if(runState == true)
        {
            runState = false;
            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            return;
        }
    }

    public void update()
    {
        int tmpMapIndex=m.index;

        m.update();
        player.update();

        if(m.index != tmpMapIndex)
        {
            //player.pos.set(1* Tile.tileWidth,20*Tile.tileHeight);
            player.setX(1 * tileWidth);
            player.setY(20 * tileHeight);
        }

    }

    public void draw()
    {
        bs = wnd.GetCanvas().getBufferStrategy();
        if(bs == null)
        {
            try
            {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        Map.draw();
        player.draw();
        //jumper1.draw(player);
        //jumper2.draw(player);


        bs.show();
        g.dispose();
    }

}
