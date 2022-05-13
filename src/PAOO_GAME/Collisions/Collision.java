package PAOO_GAME.Collisions;

import PAOO_GAME.Constants;
import PAOO_GAME.Game;
import PAOO_GAME.Map.Map;

public class Collision {
    public static boolean checkCollision(int xA,int yA,int widthA,int heightA,
                                         int xB,int yB,int widthB,int heightB)
    {
        if(xA+widthA >= xB &&
            xB+widthB >= xA &&
            yA+heightA >= yB &&
            yB+heightB >= yA)
        {
            return true;
        }
        return false;
    }

    public static boolean checkCollisions(int _x, int _y,int widthA,int heightA, int []objectType)
    {
        int x,y;
        int wallWidth= Constants.tileWidth;
        int wallHeight = Constants.tileHeight;

        for(int i=0;i< Game.heightTiles;i++)
        {
            for(int j=0;j<Game.widthTiles;j++)
            {
                for ( int k =0 ; k< objectType.length;k++){
                    if (Map.actualMap[i][j] == objectType[k]) {
                        x = j * wallWidth;
                        y = i * wallHeight;

                        if (checkCollision(_x, _y, widthA, heightA,
                                x, y, wallWidth, wallHeight)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /*public static boolean checkNextLevel(int _x,int _y,int widthA,int heightA, int objectType)
    {
        int x,y;
        int wallWidth= Tile.tileWidth;
        int wallHeight= Tile.tileHeight;

        for(int i=0;i< Game.heightTiles;i++)
        {
            for(int j=0;j<Game.widthTiles;j++)
            {
                if (Map.actualMap[i][j]==objectType)
                {
                    x=j*wallWidth;
                    y=i*wallHeight;

                    if(checkCollision(_x,_y,widthA,heightA,
                                    x,y,wallWidth-10,wallHeight-10))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }*/
}
