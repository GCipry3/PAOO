package PAOO_GAME;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Collisions.MouseControl;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.GameWindow.GameWindow;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Map.Map;
import PAOO_GAME.Player.Player;
import PAOO_GAME.Player.PlayerFactory;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static PAOO_GAME.Constants.*;

public final class Game extends Component implements Runnable {

    private GameWindow wnd;
    private boolean runState;

    public static Graphics g;

    public Player player;
    public Map m;

    public List<Drawable> listWithDrawable=new ArrayList<>();

    private boolean loseFlag=false;
    private boolean winFlag=false;
    private int state=0;
    private Clip clip;
    private static boolean musicOn=false;
    //0-start
    //1-choose player
    //2-choose map
    //3-play
    //4-endGame
    //5-winCase
    //6-loseCase
    //7-resetOrQuit

    private final String musicPath="resources\\music\\music.wav";

    public static Game instance=null;

    private Game()
    {
        runState = false;
    }

    public static Game getInstance(){
        if(instance == null){
            instance=new Game();
        }
        return instance;
    }

    public void setLoseFlag(){loseFlag=true;}
    public void setWinFlag() {winFlag =true;}

    private void InitGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        wnd = new GameWindow("Attack on Ninja)",
                widthNrTiles *tileWidth,
                heightNrTiles *tileHeight);

        wnd.BuildGameWindow();

        Assets.Init();

        AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(new File(musicPath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        //clip.start();
        //clip.loop(10);
    }

    @Override
    public void run()
    {
        try {
            InitGame();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
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
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public void update() throws IOException {
        boolean selected=false;
        switch (state){
            case 0:
                if(touchedRectangle(widthNrTiles*tileWidth-400,4*64+37,250,50)){
                    state=1;
                    MouseControl.getInstance().resetCoords();
                }
                if(touchedRectangle(widthNrTiles*tileWidth-400,5*64+37,250,50)){
                    if(musicOn) {
                        clip.stop();
                        musicOn=false;
                    }else{
                        clip.start();
                        musicOn=true;
                    }
                    MouseControl.getInstance().resetCoords();
                }
                if(touchedRectangle(widthNrTiles*tileWidth-400,6*64+40,250,50)){
                    StopGame();
                }
                break;

            case 1:
                PlayerFactory pFactory=new PlayerFactory();

                if(touchedRectangle(150,250,430,500)){
                    pFactory.createPlayer("black");
                    selected=true;
                }
                if(touchedRectangle(150+435,250,430,500)){
                    pFactory.createPlayer("green");
                    selected=true;
                }
                if(touchedRectangle(150+2*435,250,430,500)){
                    pFactory.createPlayer("blue");
                    selected=true;
                }

                if(selected){
                    state=2;
                    player=pFactory.getPlayer();
                    listWithDrawable.add(player);
                    MouseControl.getInstance().resetCoords();
                }
                break;

            case 2:
                if(touchedRectangle(50,200,480,500)){
                    m=new Map(0);
                    selected=true;
                }
                if(touchedRectangle(50+500,200,480,500)){
                    m=new Map(1);
                    selected=true;
                }
                if(touchedRectangle(50+2*500,200,480,500)){
                    m=new Map(2);
                    selected=true;
                }
                if(selected)
                {
                    state = 3;
                    m.init();
                    listWithDrawable.add(0,m);
                    GameWindow.setAllToVisible();
                    MouseControl.getInstance().resetCoords();
                }
                break;

            case 3:
                if (!loseFlag && !winFlag) {
                    updateAfterStart();
                }else{
                    state=4;
                    GameWindow.setAllToNotVisible();
                }
                break;

            case 4:
                if(loseFlag){
                    state=6;
                }else{
                    state=5;
                }
                break;

            case 5,6:
                if(touchedRectangle(widthNrTiles*tileWidth-300,heightNrTiles*tileHeight-100,250,60)){
                    state=7;
                }
                break;
            case 7:
                //reset all
                listWithDrawable.clear();
                m=null;
                player=null;
                loseFlag=false;
                winFlag=false;
                Player.setEndAttack(true);
                state=0;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
    }

    public void updateAfterStart(){
        listWithDrawable.forEach(Drawable::update);
    }

    public boolean touchedRectangle(int x,int y,int width,int height){
        int xMouse= MouseControl.getInstance().getX();
        int yMouse= MouseControl.getInstance().getY();

        return Collision.checkCollision(
          x,y,width,height,
          xMouse,yMouse,1,1
        );
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

        switch (state){
            case 0:
                Drawer.draw(0,0,Assets.startPageBackground,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(widthNrTiles*tileWidth-400,4*64+32,Assets.buttons.get(0),250,60);
                Drawer.draw(widthNrTiles*tileWidth-400,5*64+32,Assets.buttons.get(4),250,60);
                Drawer.draw(widthNrTiles*tileWidth-400,6*64+32,Assets.buttons.get(2),250,60);
                break;

            case 1:
                Drawer.draw(0,0,Assets.selectPlayerPage,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(150,        250,Assets.selectPlayerBox,430,500);
                Drawer.draw(150+435,    250,Assets.selectPlayerBox,430,500);
                Drawer.draw(150+2*435,  250,Assets.selectPlayerBox,430,500);

                break;

            case 2:
                Drawer.draw(0,0,Assets.mapSelectPage,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(50,        200,Assets.mapsScreenshot.get(0),480,500);
                Drawer.draw(50+500,    200,Assets.mapsScreenshot.get(1),480,500);
                Drawer.draw(50+2*500,  200,Assets.mapsScreenshot.get(2),480,500);
                break;

            case 3:
                listWithDrawable.forEach(Drawable::draw);
                movementKeyDraw();
                break;

            case 5:
                Drawer.draw(0,0,Assets.winPage,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(widthNrTiles*tileWidth-300,heightNrTiles*tileHeight-100,Assets.buttons.get(3),250,60);
                break;
            case 6:
                Drawer.draw(0,0,Assets.losePage,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(widthNrTiles*tileWidth-300,heightNrTiles*tileHeight-100,Assets.buttons.get(3),250,60);
                break;
            case 7:
                //TODO
                break;
        }
        bs.show();
        g.dispose();
    }

    public void movementKeyDraw(){
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
    }
}
