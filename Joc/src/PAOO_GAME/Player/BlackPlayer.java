package PAOO_GAME.Player;

import PAOO_GAME.Component.Coord;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Map.Map;
import PAOO_GAME.Tiles.Tile;
import PAOO_GAME.Player.Player.*;

public class BlackPlayer extends Player {

    private int jumps=0;
    private boolean ok=true;
    private boolean okFall=true;
    private boolean right=false;
    private int atackCnt=0;
    private boolean endAtack=true;

    public BlackPlayer(String _name)
    {
        name=_name;
        lives =3;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void Init() {
        lives =3;
    }

    @Override
    public void atack() {
        if(KeyboardControl.atack)
        {
            endAtack=false;
        }
        if(endAtack==false)
        {
            KeyboardControl.atack=false;
            atackCnt++;
        }
        if(atackCnt>60)
        {
            endAtack=true;
            atackCnt=0;
        }
    }

    @Override
    public void draw() {
        if(right)
        {
            Drawer.draw(pos,Assets.blackNinjaRight,width*scale,height*scale);
        }
        else
        {
            Drawer.draw(pos,Assets.blackNinjaLeft,width*scale,height*scale);
        }

        if(endAtack==false)
        {
            Drawer.draw(new Coord(pos.getX()-32,pos.getY()-32),Assets.ShurikenBg,128,128);
        }
    }

    @Override
    public void update() {
        atack();
        Coord tmp=new Coord(pos.getX(),pos.getY());
        tmp.setX(pos.getX()+KeyboardControl.velocity.getX()*speed);

        if(KeyboardControl.velocity.getX()==1)
        {
            right=true;
        }

        if(KeyboardControl.velocity.getX()==-1)
        {
            right=false;
        }

        if(jumps<jumpTimes && ok)
        {
            tmp.setY(pos.getY()+KeyboardControl.velocity.getY()*speed*jumpScale);
            if(KeyboardControl.velocity.getY() == -1)
            {
                jumps++;
                okFall=false;
                //System.out.println("Print Jumps: "+jumps);
            }
        }
        else
        {
            tmp.setY(pos.getY());
            jumps=0;
            ok=false;
        }

        if(!ok)
        {
            tmp.setY(tmp.getY()+1);
            if(Collision.checkAllWallCollisions(new Coord(tmp.getX(),tmp.getY()),width*scale-10,height*scale-10))
            {
               ok=true;
               okFall=true;
            }
        }

        if(!Collision.checkAllWallCollisions(new Coord(tmp.getX(),tmp.getY()),width*scale-10,height*scale-10))
        {
            pos.set(tmp.getX(),tmp.getY());
        }

        if(true) {
            tmp.setY(tmp.getY() + 1);
            if (!Collision.checkAllWallCollisions(new Coord(tmp.getX(), tmp.getY()), width * scale - 10, height * scale - 10)) {
                pos.set(tmp.getX(), tmp.getY());
            }
        }

        if(Collision.checkNextLevel(this.getCoords(),this.getWidth(),this.getHeight()))
        {
            Map.index++;
        }

    }

    @Override
    public boolean increaseLife()
    {
        if(lives<3)
        {
            lives++;
            return true;
        }
        return false;
    }
}
