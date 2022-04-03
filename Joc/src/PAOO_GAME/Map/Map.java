package PAOO_GAME.Map;
import PAOO_GAME.Component.Coord;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Game;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Graphics.ImageLoader;
import PAOO_GAME.Tiles.Tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Map {
    private static int rows=25;
    private static int cols=50;
    public static int[][][] map =new int[3][rows][cols];
    public static int actualMap[][]=new int[rows][cols];
    public static int nrMaps;
    public static int level;
    public static int index;

    public Map(int _level)
    {
        level=_level;
        switch(level)
        {
            case 0:
                nrMaps=2;
                break;
            case 1:
                nrMaps=3;
                break;
            case 2:
                nrMaps=4;
                break;
        }
        index=0;
    }

    public static void readMap(String path,int mapNr) throws IOException
    {
        Scanner input=new Scanner(new File(path));

        for(int i = 0 ; i<rows && input.hasNextLine();i++)
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
            readMap("resources/maps/map1_1.txt",0);
            readMap("resources/maps/map1_2.txt",1);
        }
    }

    public void update()
    {
        actualMap=map[index];
    }

    public static void draw()
    {
        BufferedImage img = ImageLoader.LoadImage("resources/background.png");
        Coord pos=new Coord(0,0);
        Drawer.draw(pos, img, Game.widthTiles* Tile.tileWidth,Game.heightTiles*Tile.tileHeight);

        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                switch(actualMap[i][j])
                {
                    case 1:
                        Drawer.draw(new Coord(j*32,i*32), Assets.rock,32,32);
                        break;
                    case 6:
                        Drawer.draw(new Coord(j*32,i*32), Assets.coin,32,32);
                        break;
                    case 8:
                        Drawer.draw(new Coord(j*32,i*32), Assets.diamond,48,48);
                        break;
                    case 7:
                        Drawer.draw(new Coord(j*32,i*32), Assets.gold,48,48);
                        break;

                }
            }
        }
    }

}
