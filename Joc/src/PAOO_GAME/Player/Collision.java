package PAOO_GAME.Player;

import PAOO_GAME.Component.Coord;
import PAOO_GAME.Game;
import PAOO_GAME.Map.Map;
import PAOO_GAME.Tiles.Tile;

public class Collision {
    public static boolean checkCollision(Coord A,int widthA,int heightA,Coord B,int widthB,int heightB)
    {
        if(A.getX()+widthA >= B.getX() &&
            B.getX()+widthB >= A.getX() &&
            A.getY()+heightA >= B.getY() &&
            B.getY()+heightB >= A.getY())
        {
            return true;
        }
        return false;
    }

    public static boolean checkAllWallCollisions(Coord A,int widthA,int heightA)
    {
        int x,y;
        int wallWidth= Tile.tileWidth;
        int wallHeight= Tile.tileHeight;

        for(int i=0;i< Game.heightTiles;i++)
        {
            for(int j=0;j<Game.widthTiles;j++)
            {
                if (Map.actualMap[i][j]==1)
                {
                    x=j*wallWidth;
                    y=i*wallHeight;

                    if(checkCollision(A,widthA,heightA,new Coord(x,y),wallWidth,wallHeight))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean checkNextLevel(Coord A,int widthA,int heightA)
    {
        int x,y;
        int wallWidth= Tile.tileWidth;
        int wallHeight= Tile.tileHeight;

        for(int i=0;i< Game.heightTiles;i++)
        {
            for(int j=0;j<Game.widthTiles;j++)
            {
                if (Map.actualMap[i][j]==2)
                {
                    x=j*wallWidth;
                    y=i*wallHeight;

                    if(checkCollision(A,widthA,heightA,new Coord(x,y),wallWidth-10,wallHeight-10))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
