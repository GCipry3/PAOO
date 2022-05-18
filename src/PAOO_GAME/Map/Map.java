package PAOO_GAME.Map;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Constants;
import PAOO_GAME.Drawable;
import PAOO_GAME.Enemy.Goblin;
import PAOO_GAME.Enemy.Ogre;
import PAOO_GAME.Graphics.ImageLoader;
import PAOO_GAME.Powers.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;

public class Map implements Drawable {
    public static int[][][] map     =new int[3][heightNrTiles][widthNrTiles];
    public static int[][] actualMap =new int[heightNrTiles][widthNrTiles];
    public static int nrMaps;
    public static int level;
    public static int index;
    private static int oldIndexOfMap;

    private static final List<Drawable> listOfDrawables =new ArrayList<>();

    public Map(int _level)
    {
        level=_level;
        nrMaps=2;
        index=0;
        oldIndexOfMap =0;
    }

    public static void readMap(String path,int mapNr) throws IOException
    {
        Scanner input=new Scanner(new File(path));

        for(int i = 0; i< heightNrTiles && input.hasNextLine(); i++)
        {
            String[] line=input.nextLine().trim().split(" ");
            for(int j = 0 ;j<line.length;j++)
            {
                map[mapNr][i][j]=Integer.parseInt(line[j]);
            }
        }

    }

    public void init() throws IOException {
        if(level==0)
        {
            readMap("resources/maps/map3_2.txt",0);
            readMap("resources/maps/map1_2.txt",1);
        }

        actualMap=map[index];

        updateListWithObjects();
    }



    public void update()
    {
        if(oldIndexOfMap !=index){
            actualMap=map[index];
            oldIndexOfMap =index;
            listOfDrawables.clear();
            updateListWithObjects();
            player.setX(tileWidth);
            player.setY(20 * tileHeight);
            jumpHeight=64;
        }

        listOfDrawables.forEach(Drawable::update);
    }

    private void updateListWithObjects() {
        for (int i = 0; i< heightNrTiles; i++){
            for(int j = 0; j< widthNrTiles; j++){
                switch (actualMap[i][j]) {
                    case 1 -> listOfDrawables.add(new Rock(j * 32, i * 32, 32, 32));
                    case 6 -> listOfDrawables.add(new Coin(j * 32, i * 32, 32, 32));
                    case 8 -> listOfDrawables.add(new Diamond(j * 32, i * 32, 48, 48));
                    case 7 -> listOfDrawables.add(new Gold(j * 32, i * 32, 48, 48));
                    case 9 -> listOfDrawables.add(new Jumper(j * 32, i * 32, 64, 64));
                    case 4 -> listOfDrawables.add(new Goblin(j * 32, i * 32));
                    case 10 ->listOfDrawables.add(new Ogre(j * 32, i * 32));
                }
            }
        }
    }

    public void draw()
    {
        BufferedImage bg = ImageLoader.LoadImage("resources/background.png");
        Drawer.draw(0,0, bg,
                Constants.widthNrTiles * tileWidth,
                Constants.heightNrTiles *tileHeight
        );

        listOfDrawables.forEach(Drawable::draw);
    }

}
