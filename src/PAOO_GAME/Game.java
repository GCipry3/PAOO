package PAOO_GAME;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Collisions.KeyboardControl;
import PAOO_GAME.Collisions.MouseControl;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.GameWindow.GameWindow;
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
import static PAOO_GAME.Graphics.Assets.*;

public final class Game extends Component implements Runnable {

    private GameWindow wnd;
    private boolean runState;

    public static Graphics g;

    public static Player player;
    public static Map m;

    public static List<Drawable> listWithDrawable=new ArrayList<>();

    private static boolean loseFlag=false;
    private static boolean winFlag=false;
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

    public static void setLoseFlag(){loseFlag=true;}
    public static void setWinFlag() {winFlag =true;}

    private void InitGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        wnd = new GameWindow("Attack on Ninja)",
                widthNrTiles *tileWidth,
                heightNrTiles *tileHeight);

        wnd.BuildGameWindow();

        Init();

        String musicPath = "resources\\music\\music.wav";
        AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(new File(musicPath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    /*public static int getPlayerX(){return player.getX();}
    public static int getPlayerY(){return player.getY();}
    public static boolean getPlayerAttackStatus(){return player.getAttackStatus();}
    public static void playerIncreaseCoin(){
        player.increaseCoins();
        GameWindow.setCoins(player.getCoins());
    }
    public static void playerSetGoldCollected(){player.setGoldCollected();}
    public static void setPlayerX(int x){player.setX(x);}
    public static void setPlayerY(int y){player.setY(y);}

    public static void mapIncreaseIndexOfMap(){Map.increaseIndexOfMap();}
    public static boolean mapCompareIndexWithNrMaps(){return Map.compareIndexWithNrMaps();}*/

    public static int getGameWindowShurikenCounter(){return GameWindow.getShurikenCounter();}
    public static void setGameWindowShurikenCounter(int x){GameWindow.setShurikenCounter(x);}
    //public static void setGameWindowLifeBarStatus(int x){GameWindow.setLifeBarStatus(x);}

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
                } catch (Exception e) {
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

    public void update() {
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
                    GameWindow.setLifeBarStatus(player.getLifeStatus());
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
                    try{
                        m.init();
                    }catch (Exception e){
                        System.out.println("map exception");
                    }

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
                //player.setEndAttack(true);
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
                Drawer.draw(0,0,startPageBackground,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(widthNrTiles*tileWidth-400,4*64+32,buttons.get(0),250,60);
                Drawer.draw(widthNrTiles*tileWidth-400,5*64+32,buttons.get(4),250,60);
                Drawer.draw(widthNrTiles*tileWidth-400,6*64+32,buttons.get(2),250,60);
                break;

            case 1:
                Drawer.draw(0,0,selectPlayerPage,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(150,        250,selectPlayerBox,430,500);
                Drawer.draw(150+435,    250,selectPlayerBox,430,500);
                Drawer.draw(150+2*435,  250,selectPlayerBox,430,500);

                break;

            case 2:
                Drawer.draw(0,0,mapSelectPage,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(50,        200,mapsScreenshot.get(0),480,500);
                Drawer.draw(50+500,    200,mapsScreenshot.get(1),480,500);
                Drawer.draw(50+2*500,  200,mapsScreenshot.get(2),480,500);
                break;

            case 3:
                listWithDrawable.forEach(Drawable::draw);
                movementKeyDraw();
                break;

            case 5:
                Drawer.draw(0,0,winPage,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(widthNrTiles*tileWidth-300,heightNrTiles*tileHeight-100,buttons.get(3),250,60);
                break;
            case 6:
                Drawer.draw(0,0,losePage,widthNrTiles*tileWidth,heightNrTiles*tileHeight);
                Drawer.draw(widthNrTiles*tileWidth-300,heightNrTiles*tileHeight-100,buttons.get(3),250,60);
                break;
            case 7:
                //TODO
                break;
        }
        bs.show();
        g.dispose();
    }

    public void movementKeyDraw(){
        if(KeyboardControl.dPressed){
            Drawer.draw(widthNrTiles*tileWidth-2*64,4*64,right,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-2*64,4*64,rightT,64,64);
        }
        if(KeyboardControl.sPressed){
            Drawer.draw(widthNrTiles*tileWidth-3*64,4*64,down,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-3*64,4*64,downT,64,64);
        }
        if(KeyboardControl.aPressed){
            Drawer.draw(widthNrTiles*tileWidth-4*64,4*64,left,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-4*64,4*64,leftT,64,64);
        }
        if(KeyboardControl.ePressed){
            Drawer.draw(widthNrTiles*tileWidth-2*64,3*64,shurikenAttackButton,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-2*64,3*64,shurikenAttackButtonT,64,64);
        }
        if(KeyboardControl.wPressed){
            Drawer.draw(widthNrTiles*tileWidth-3*64,3*64,up,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-3*64,3*64,upT,64,64);
        }
        if(KeyboardControl.qPressed){
            Drawer.draw(widthNrTiles*tileWidth-4*64,3*64,shurikenCircleButton,64,64);
        }else{
            Drawer.draw(widthNrTiles*tileWidth-4*64,3*64,shurikenCircleButtonT,64,64);
        }
    }
}
