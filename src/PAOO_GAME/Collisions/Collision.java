package PAOO_GAME.Collisions;

import PAOO_GAME.Map.Map;

import static PAOO_GAME.Constants.*;

public class Collision {
    public static boolean checkCollision(int xA,int yA,int widthA,int heightA,
                                         int xB,int yB,int widthB,int heightB)
    {
        return xA + widthA >= xB &&
                xB + widthB >= xA &&
                yA + heightA >= yB &&
                yB + heightB >= yA;
    }

    public static boolean checkCollisions(int _x, int _y,int widthA,int heightA, int []objectType)
    {
        int x,y;
        int wallWidth= tileWidth;
        int wallHeight = tileHeight;

        for(int i = 0; i< heightNrTiles; i++)
        {
            for(int j = 0; j<widthNrTiles; j++)
            {
                for (int value : objectType) {
                    if (Map.actualMap[i][j] == value) {
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

}
