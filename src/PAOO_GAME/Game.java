package PAOO_GAME;

import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.GameWindow.GameWindow;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Map.Map;
import PAOO_GAME.Player.Player;
import PAOO_GAME.Player.PlayerFactory;

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

    public PlayerFactory pFactory=new PlayerFactory();
    public Player player;
    public Map m=new Map(0);

    public List<Drawable> listWithDrawable=new ArrayList<>();

    private boolean loseFlag=false;
    private boolean winFlag=false;

    public static Game instance=null;

    private Game()
    {
        runState = false;
        pFactory.createPlayer("black");
        player=pFactory.getPlayer();
        listWithDrawable.add(player);
    }

    public static Game getInstance(){
        if(instance == null){
            instance=new Game();
        }
        return instance;
    }

    public void setLoseFlag(){loseFlag=true;}
    public void setWinFlag() {winFlag =true;}

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
        if(!loseFlag && !winFlag) {
            listWithDrawable.forEach(Drawable::update);
        }/*else if(loseFlag){

        }else{

        }*/
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

        if(KeyboardControl.d){
            Drawer.draw(widthNrTiles*tileWidth-2*64,4*64,Assets.right,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-2*64,4*64,Assets.rightT,64,64);
        }
        if(KeyboardControl.s){
            Drawer.draw(widthNrTiles*tileWidth-3*64,4*64,Assets.down,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-3*64,4*64,Assets.downT,64,64);
        }
        if(KeyboardControl.a){
            Drawer.draw(widthNrTiles*tileWidth-4*64,4*64,Assets.left,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-4*64,4*64,Assets.leftT,64,64);
        }
        if(KeyboardControl.e){
            Drawer.draw(widthNrTiles*tileWidth-2*64,3*64,Assets.shurikenAttackButton,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-2*64,3*64,Assets.shurikenAttackButtonT,64,64);
        }
        if(KeyboardControl.w){
            Drawer.draw(widthNrTiles*tileWidth-3*64,3*64,Assets.up,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-3*64,3*64,Assets.upT,64,64);
        }
        if(KeyboardControl.q){
            Drawer.draw(widthNrTiles*tileWidth-4*64,3*64,Assets.shurikenCircleButton,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-4*64,3*64,Assets.shurikenCircleButtonT,64,64);
        }

        bs.show();
        g.dispose();
    }

}
