package PAOO_GAME.Graphics;

import java.awt.image.BufferedImage;

public final class Assets {

    public static BufferedImage blackNinjaLeft;
    public static BufferedImage blackNinjaRight;
    public static BufferedImage greenNinjaLeft;
    public static BufferedImage greenNinjaRight;
    public static BufferedImage blueNinjaLeft;
    public static BufferedImage blueNinjaRight;

    public static BufferedImage firstOgre;
    public static BufferedImage secondOgre;
    public static BufferedImage thirdOgre;

    public static BufferedImage firstGoblin;
    public static BufferedImage secondGoblin;
    public static BufferedImage thirdGoblin;

    public static BufferedImage coin ;
    public static BufferedImage diamond ;
    public static BufferedImage gold ;
    public static BufferedImage jumpLeft;
    public static BufferedImage jumpRight;

    public static BufferedImage checkpoint;
    public static BufferedImage longPlatform;
    public static BufferedImage longTallPlatform;
    public static BufferedImage shortPlatform;

    public static BufferedImage background1;
    public static BufferedImage ShurikenBg ;
    public static BufferedImage rock;
    public static BufferedImage rock2;

    public static void Init() {
        rock            =ImageLoader.LoadImage("resources/static/rock.png");
        rock2           =ImageLoader.LoadImage("resources/static/rock2.png");
        firstGoblin     =ImageLoader.LoadImage("resources/goblin/Goblin1_1.png");
        secondGoblin    =ImageLoader.LoadImage("resources/goblin/Goblin2_1.png");
        thirdGoblin     =ImageLoader.LoadImage("resources/goblin/Goblin3_1.png");

        firstOgre       =ImageLoader.LoadImage("resources/ogre/Ogre1_1.png");
        secondOgre      =ImageLoader.LoadImage("resources/ogre/Ogre2_1.png");
        thirdOgre       =ImageLoader.LoadImage("resources/ogre/Ogre3_1.png");

        ShurikenBg      =ImageLoader.LoadImage("resources/powers/shuriken.png");

        blackNinjaRight =ImageLoader.LoadImage("resources/ninja/black/Ninja1.png");
        blackNinjaLeft  =ImageLoader.LoadImage("resources/ninja/black/Ninja1left.png");

        blueNinjaRight  =ImageLoader.LoadImage("resources/ninja/blue/Ninja1.png");
        blueNinjaLeft   =ImageLoader.LoadImage("resources/ninja/blue/Ninja2.png");

        greenNinjaRight =ImageLoader.LoadImage("resources/ninja/green/Ninja1.png");
        greenNinjaLeft  =ImageLoader.LoadImage("resources/ninja/green/Ninja2.png");

        diamond         =ImageLoader.LoadImage("resources/powers/diamond.png");
        gold            =ImageLoader.LoadImage("resources/powers/gold.png");
        coin            =ImageLoader.LoadImage("resources/powers/coin.png");

        background1     =ImageLoader.LoadImage("resources/maps/Map1_1.png");

        jumpLeft        =ImageLoader.LoadImage("resources/powers/jumpLeft.png");
        jumpRight       =ImageLoader.LoadImage("resources/powers/jumpRight.png");

        checkpoint      =ImageLoader.LoadImage("resources/static/checkpoint.png");

    }

}
