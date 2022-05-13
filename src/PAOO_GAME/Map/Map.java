package PAOO_GAME.Map;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Game;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Graphics.ImageLoader;
import PAOO_GAME.Powers.*;
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
    private static int oldindex;

    private static List<Powers> listOfObjects=new ArrayList<>();

    public Map(int _level)
    {
        level=_level;
        nrMaps=2;
        /*switch(level)
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
        }*/
        index=0;
        oldindex=0;
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

        actualMap=map[index];

        updateListWithObjects();

    }



    public void update()
    {
        if(oldindex!=index){
            actualMap=map[index];
            oldindex=index;
            listOfObjects.clear();
            updateListWithObjects();
        }
        for(int i=0;i<listOfObjects.size();i++){
            listOfObjects.get(i).update();
        }
    }

    private void updateListWithObjects() {
        for (int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                switch(actualMap[i][j])
                {
                    case 1:
                        listOfObjects.add(new Rock(j*32,i*32,32,32));
                        break;
                    case 6:
                        listOfObjects.add(new Coin(j*32,i*32,32,32));
                        break;
                    case 8:
                        listOfObjects.add(new Diamond(j*32,i*32,48,48));
                        break;
                    case 7:
                        listOfObjects.add(new Gold(j*32,i*32,48,48));
                        break;

                }
            }
        }
    }

    public static void draw()
    {
        BufferedImage img = ImageLoader.LoadImage("resources/background.png");
        Drawer.draw(0,0, img, Game.widthTiles* Tile.tileWidth,Game.heightTiles*Tile.tileHeight);

        for(int i=0;i<listOfObjects.size();i++){
            listOfObjects.get(i).draw();
        }
    }

}
