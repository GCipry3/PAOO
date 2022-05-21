package PAOO_GAME.Map;

import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Drawable;
import PAOO_GAME.Enemy.Goblin;
import PAOO_GAME.Enemy.Ogre;
import PAOO_GAME.Game;
import PAOO_GAME.Map.Powers.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Graphics.Assets.background;

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

    public static void increaseIndexOfMap(){index++;}
    public static boolean compareIndexWithNrMaps(){return index>=nrMaps;}

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
            readMap("resources/maps/map1_1.txt",0);
            readMap("resources/maps/map1_2.txt",1);
        }else if(level==1){
            readMap("resources/maps/map2_1.txt",0);
            readMap("resources/maps/map2_2.txt",1);
        }else if(level==2){
            readMap("resources/maps/map3_1.txt",0);
            readMap("resources/maps/map3_2.txt",1);
        }else{
            throw new RuntimeException("Level "+level+" doesn't exist");
        }

        actualMap=map[index];

        listOfDrawables.clear();
        try {
            updateListWithObjects();
        }catch (ElementNotFoundOnMapException e){
            System.out.println(e.getMessage());
            System.out.println("Problems at Map's matrix!!!!!!");
            System.out.println("Problems at Map's matrix!!!!!!");
            System.out.println("Problems at Map's matrix!!!!!!");
        }
    }



    public void update()
    {
        if(oldIndexOfMap !=index){
            actualMap=map[index];
            oldIndexOfMap =index;
            
            listOfDrawables.clear();
            try {
                updateListWithObjects();
            }catch (ElementNotFoundOnMapException e){
                System.out.println("Problems at Map's matrix!!!!!!");
                System.out.println("Problems at Map's matrix!!!!!!");
                System.out.println("Problems at Map's matrix!!!!!!");
                System.out.println("Problems at Map's matrix!!!!!!");
            }
            Game.getInstance().setPlayerX(tileWidth+5);
            Game.getInstance().setPlayerY(20 * tileHeight-5);
            jumpHeight=64;
        }

        listOfDrawables.forEach(Drawable::update);
    }

    public static class ElementNotFoundOnMapException extends Exception{
        public ElementNotFoundOnMapException(int x){
            super("The element "+x+" is not recognized");
        }
    }

    private void updateListWithObjects() throws ElementNotFoundOnMapException {
        for (int i = 0; i< heightNrTiles; i++){
            for(int j = 0; j< widthNrTiles; j++){
                int x=j*32;
                int y=i*32;
                switch (actualMap[i][j]) {
                    case 0 -> {
                        //air
                    }
                    case 1 -> listOfDrawables.add(new Rock      (x, y, 32, 32));
                    case 5 -> listOfDrawables.add(new Grass     (x, y, 32, 32));
                    case 2 -> listOfDrawables.add(new CheckPoint(x, y, 32, 64));
                    case 6 -> listOfDrawables.add(new Coin      (x, y, 32, 32));
                    case 8 -> listOfDrawables.add(new Diamond   (x, y, 48, 48));
                    case 7 -> listOfDrawables.add(new Gold      (x, y, 48, 48));
                    case 9 -> listOfDrawables.add(new Jumper    (x, y, 64, 64));
                    case 4 -> listOfDrawables.add(new Goblin    (x, y));
                    case 10 -> listOfDrawables.add(new Ogre      (x, y));
                    default -> throw new ElementNotFoundOnMapException(actualMap[i][j]);
                }
            }
        }
    }

    public void draw()
    {
        Drawer.draw(0,0, background,
                widthNrTiles * tileWidth,
                heightNrTiles *tileHeight
        );

        listOfDrawables.forEach(Drawable::draw);
    }

}
