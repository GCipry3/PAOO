package PAOO_GAME.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public final class Assets {

    public static BufferedImage background;
    public static BufferedImage blackNinjaLeft;
    public static BufferedImage blackNinjaRight;
    public static BufferedImage greenNinjaLeft;
    public static BufferedImage greenNinjaRight;
    public static BufferedImage blueNinjaLeft;
    public static BufferedImage blueNinjaRight;
    public static BufferedImage blackNinjaLeft1;
    public static BufferedImage blackNinjaRight1;
    public static BufferedImage greenNinjaLeft1;
    public static BufferedImage greenNinjaRight1;
    public static BufferedImage blueNinjaLeft1;
    public static BufferedImage blueNinjaRight1;

    public static List<BufferedImage> ogreRight =new ArrayList<>();
    public static List<BufferedImage> goblinRight =new ArrayList<>();
    public static List<BufferedImage> ogreLeft=new ArrayList<>();
    public static List<BufferedImage> goblinLeft=new ArrayList<>();

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

    public static BufferedImage up;
    public static BufferedImage down;
    public static BufferedImage left;
    public static BufferedImage right;
    public static BufferedImage shurikenAttackButton;
    public static BufferedImage shurikenCircleButton;
    public static BufferedImage upT;
    public static BufferedImage downT;
    public static BufferedImage leftT;
    public static BufferedImage rightT;
    public static BufferedImage shurikenAttackButtonT;
    public static BufferedImage shurikenCircleButtonT;

    public static List<BufferedImage> shuriken=new ArrayList<>();

    public static BufferedImage grass;

    public static BufferedImage startPageBackground ;
    public static List<BufferedImage> buttons=new ArrayList<>();
    public static BufferedImage selectPlayerPage;
    public static BufferedImage selectPlayerBox;

    public static BufferedImage mapSelectPage;
    public static List<BufferedImage> mapsScreenshot=new ArrayList<>();

    public static BufferedImage losePage;
    public static BufferedImage winPage;

    public static void Init() {
        background = ImageLoader.LoadImage("resources/background.png");

        losePage=ImageLoader.LoadImage("resources/static/loseBg.png");
        winPage=ImageLoader.LoadImage("resources/static/winBg.png");
        mapSelectPage=ImageLoader.LoadImage("resources/static/mapsSelectPage.png");
        mapsScreenshot.add(ImageLoader.LoadImage("resources/maps/level1.png"));
        mapsScreenshot.add(ImageLoader.LoadImage("resources/maps/level2.png"));
        mapsScreenshot.add(ImageLoader.LoadImage("resources/maps/level3.png"));

        selectPlayerPage=ImageLoader.LoadImage("resources/static/selectPlayer.png");
        selectPlayerBox=ImageLoader.LoadImage("resources/static/frame.png");

        SpriteSheet shurikenSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("resources/static/shurikenSpriteSheet.png"),500,500);
        shuriken.add(shurikenSpriteSheet.crop(0,0));
        shuriken.add(shurikenSpriteSheet.crop(1,0));
        shuriken.add(shurikenSpriteSheet.crop(2,0));
        shuriken.add(shurikenSpriteSheet.crop(3,0));

        SpriteSheet keyButtons = new SpriteSheet(ImageLoader.LoadImage("resources/static/buttons1.png"),87,76);
        SpriteSheet keyButtonsTransparent = new SpriteSheet(ImageLoader.LoadImage("resources/static/buttons1_t1.png"),87,76);

        up=keyButtons.crop(0,1);
        down=keyButtons.crop(0,2);
        right=keyButtons.crop(0,3);
        left=keyButtons.crop(0,4);
        shurikenAttackButton=keyButtons.crop(2,3);
        shurikenCircleButton=keyButtons.crop(2,0);

        upT=keyButtonsTransparent.crop(0,1);
        downT=keyButtonsTransparent.crop(0,2);
        rightT=keyButtonsTransparent.crop(0,3);
        leftT=keyButtonsTransparent.crop(0,4);
        shurikenAttackButtonT=keyButtonsTransparent.crop(2,3);
        shurikenCircleButtonT=keyButtonsTransparent.crop(2,0);

        SpriteSheet buttonsSheet= new SpriteSheet(ImageLoader.LoadImage("resources/static/buttons.png"),225,62);

        buttons.add(buttonsSheet.crop(0,0));//play
        buttons.add(buttonsSheet.crop(0,3));//options
        buttons.add(buttonsSheet.crop(0,5));//exit
        buttons.add(buttonsSheet.crop(0,2));//restart
        buttons.add(buttonsSheet.crop(0,1));//music

        goblinRight.add(ImageLoader.LoadImage("resources/goblin/Goblin1_1.png"));
        goblinRight.add(ImageLoader.LoadImage("resources/goblin/Goblin2_1.png"));
        goblinRight.add(ImageLoader.LoadImage("resources/goblin/Goblin3_1.png"));

        ogreRight.add(ImageLoader.LoadImage("resources/ogre/Ogre1_1.png"));
        ogreRight.add(ImageLoader.LoadImage("resources/ogre/Ogre2_1.png"));
        ogreRight.add(ImageLoader.LoadImage("resources/ogre/Ogre3_1.png"));

        goblinLeft.add(ImageLoader.LoadImage("resources/goblin/Goblin1_1left.png"));
        goblinLeft.add(ImageLoader.LoadImage("resources/goblin/Goblin2_1left.png"));
        goblinLeft.add(ImageLoader.LoadImage("resources/goblin/Goblin3_1left.png"));

        ogreLeft.add(ImageLoader.LoadImage("resources/ogre/Ogre1_1left.png"));
        ogreLeft.add(ImageLoader.LoadImage("resources/ogre/Ogre2_1left.png"));
        ogreLeft.add(ImageLoader.LoadImage("resources/ogre/Ogre3_1left.png"));

        startPageBackground=ImageLoader.LoadImage("resources/static/firstPage.png");

        grass           =ImageLoader.LoadImage("resources/static/grass1.png");

        rock            =ImageLoader.LoadImage("resources/static/rock.png");
        rock2           =ImageLoader.LoadImage("resources/static/rock2.png");

        ShurikenBg      =ImageLoader.LoadImage("resources/powers/shuriken.png");

        blackNinjaRight =ImageLoader.LoadImage("resources/ninja/black/Ninja1.png");
        blackNinjaLeft  =ImageLoader.LoadImage("resources/ninja/black/Ninja1left.png");

        blueNinjaRight  =ImageLoader.LoadImage("resources/ninja/blue/Ninja1.png");
        blueNinjaLeft   =ImageLoader.LoadImage("resources/ninja/blue/Ninja1left.png");

        greenNinjaRight =ImageLoader.LoadImage("resources/ninja/green/Ninja1.png");
        greenNinjaLeft  =ImageLoader.LoadImage("resources/ninja/green/Ninja1left.png");

        blackNinjaRight1 =ImageLoader.LoadImage("resources/ninja/black/Ninja2.png");
        blackNinjaLeft1  =ImageLoader.LoadImage("resources/ninja/black/Ninja2left.png");

        blueNinjaRight1  =ImageLoader.LoadImage("resources/ninja/blue/Ninja2.png");
        blueNinjaLeft1   =ImageLoader.LoadImage("resources/ninja/blue/Ninja2left.png");

        greenNinjaRight1 =ImageLoader.LoadImage("resources/ninja/green/Ninja2.png");
        greenNinjaLeft1  =ImageLoader.LoadImage("resources/ninja/green/Ninja2left.png");

        diamond         =ImageLoader.LoadImage("resources/powers/diamond.png");
        gold            =ImageLoader.LoadImage("resources/powers/gold.png");
        coin            =ImageLoader.LoadImage("resources/powers/coin.png");

        background1     =ImageLoader.LoadImage("resources/maps/Map1_1.png");

        jumpLeft        =ImageLoader.LoadImage("resources/powers/jumpLeft.png");
        jumpRight       =ImageLoader.LoadImage("resources/powers/jumpRight.png");

        checkpoint      =ImageLoader.LoadImage("resources/static/checkpoint.png");



    }

}
