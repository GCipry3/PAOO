package PAOO_GAME.Graphics;
import java.awt.image.BufferedImage;


public class SpriteSheet
{
    private BufferedImage  spriteSheet;
    private int    imageWidth  ;
    private int    imageHeight ;

    public SpriteSheet(BufferedImage buffImg,int width,int height)
    {
        imageWidth=width;
        imageHeight=height;
        spriteSheet = buffImg;
    }

    public BufferedImage crop(int x, int y)
    {
        return spriteSheet.getSubimage(x * imageWidth, y * imageHeight, imageWidth, imageHeight);
    }
}

