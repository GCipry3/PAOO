package PAOO_GAME;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.GameWindow.GameWindow;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Map.Map;
import PAOO_GAME.Player.BlackPlayer;
import PAOO_GAME.Player.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import static PAOO_GAME.Constants.*;

public class Game extends Component implements Runnable {

    private GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    //private Jumper jumper1=new Jumper(new Coord(17* tileHeight,19* tileWidth),64,64);
    //private Jumper jumper2=new Jumper(new Coord(2* tileHeight,10* tileWidth),64,64);


    public static Graphics g;

    public static Player player;
    public Map m=new Map(0);

    private Drawable []listWithDrawable=new Drawable[2];

    public static Game instance=null;

    private Game()
    {
        runState = false;
        player=new BlackPlayer();
        listWithDrawable[1]=player;
    }

    public static Game getInstance(){
        if(instance == null){
            instance=new Game();
        }
        return instance;
    }

    private void InitGame() throws IOException {
        wnd = new GameWindow("Schelet Proiect PAOO",
                widthNrTiles *tileWidth,
                heightNrTiles *tileHeight);

        m.init();

        listWithDrawable[0]=m;

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

        final double framesPerSecond   = 60;
        final double timeFrame = 1000000000 / framesPerSecond;

        while (runState)
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
        if(!runState)
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
            /*try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }*/
            System.exit(0);
        }
        else
        {
            return;
        }
    }

    public void update()
    {
        int tmpMapIndex=m.index;

        for(int i=0;i<listWithDrawable.length;i++)
        {
            listWithDrawable[i].update();
        }

        if(m.index != tmpMapIndex)
        {
            //player.pos.set(1* Tile.tileWidth,20*Tile.tileHeight);
            player.setX(1 * tileWidth);
            player.setY(20 * tileHeight);
            jumpHeight=64;
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

        for(int i=0;i<listWithDrawable.length;i++){
            listWithDrawable[i].draw();
        }

        Drawer.draw(widthNrTiles *tileWidth-450,32,Assets.coin,48,48);

        bs.show();
        g.dispose();
    }

}
