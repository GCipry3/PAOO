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
import java.util.ArrayList;
import java.util.List;

import static PAOO_GAME.Constants.*;

public final class Game extends Component implements Runnable {

    private GameWindow wnd;
    private boolean runState;

    public static Graphics g;

    public static Player player;
    public Map m=new Map(0);

    public List<Drawable> listWithDrawable=new ArrayList<>();

    public static Game instance=null;

    private Game()
    {
        runState = false;
        player=new BlackPlayer();
        listWithDrawable.add(player);
    }

    public static Game getInstance(){
        if(instance == null){
            instance=new Game();
        }
        return instance;
    }

    private void InitGame() throws IOException {
        wnd = new GameWindow("Attack on Ninja)",
                widthNrTiles *tileWidth,
                heightNrTiles *tileHeight);

        m.init();

        listWithDrawable.add(0,m);

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
        long currentTime;

        final double framesPerSecond   = 60;
        final double timeFrame = 1000000000 / framesPerSecond;

        while (runState)
        {
            currentTime = System.nanoTime();
            if((currentTime - oldTime) > timeFrame)
            {
                update();
                draw();
                oldTime = currentTime;
            }
        }

    }

    public synchronized void StartGame()
    {
        if(!runState)
        {
            runState = true;
            Thread gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public synchronized void StopGame()
    {
        if(runState)
        {
            runState = false;
            System.exit(0);
        }
    }

    public void update()
    {
        listWithDrawable.forEach(Drawable::update);
    }

    public void draw()
    {
        BufferStrategy bs = wnd.GetCanvas().getBufferStrategy();
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
        assert bs != null;
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());


        listWithDrawable.forEach(Drawable::draw);

        Drawer.draw(widthNrTiles *tileWidth-450,32,Assets.coin,48,48);

        bs.show();
        g.dispose();
    }

}
