package PAOO_GAME.Enemy;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.ShinobiShuriken;

import java.util.ArrayList;
import java.util.List;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;

public class Goblin extends Enemy{
    private final List<Projectile> listOfProjectiles= new ArrayList<>();
    private final int damage=10;
    private int cntAttack=0;

    public Goblin(int x, int y) {
        super(x, y);
    }

    @Override
    protected void attack() {
        if(playerClose()){
            if(cntAttack==0){
                listOfProjectiles.add(new Projectile(x,y+40,damage));
                cntAttack=1;
            }
            if(cntAttack>0&&cntAttack<60){
                cntAttack++;
            }
            if(cntAttack>=60){
                cntAttack=0;
            }

            int xPlayer=player.getX();
            int yPlayer=player.getY();

            if (Collision.checkCollision(
                    x,y,enemyWidth,enemyHeight,
                    xPlayer,yPlayer,playerWidth,playerHeight)){
                player.takeDamage(100);
            }

            for(int i=0;i<listOfProjectiles.size();i++){
                Projectile tmp=listOfProjectiles.get(i);
                if (Collision.checkCollision(
                        tmp.getX(),tmp.getY(),
                        tmp.getProjectileWidth(),tmp.getProjectileHeight(),
                        xPlayer,yPlayer
                        ,playerWidth,playerHeight)){
                    player.takeDamage(damage);
                }
            }
        }
    }

    @Override
    public void update() {
        if(visible) {
            attack();

            int xPlayer=player.getX();
            int yPlayer=player.getY();
            if (playerClose()) {
                move();
                //System.out.println(x+" "+y);
            }

            for(int i=0;i<listOfProjectiles.size();i++){
                listOfProjectiles.get(i).update();
                //System.out.println(i);
            }

            if (!player.getEndAttackStatus())//daca playerul ataca
            {
                if(Collision.checkCollision(
                        x,y,
                        enemyWidth,enemyHeight,
                        xPlayer-32,yPlayer-32,
                        shurikenCirclePixels,
                        shurikenCirclePixels
                )){
                    visible=false;
                }
            }

            for(int i=0;i<player.listOfShurikens.size();i++){
                ShinobiShuriken tmp=player.listOfShurikens.get(i);
                if(tmp.getVisible()){
                    if(Collision.checkCollision(
                            x,y,enemyWidth,enemyHeight,
                            tmp.getX(),tmp.getY(),
                            tmp.getProjectileWidth(),tmp.getProjectileHeight()
                    )){
                        player.listOfShurikens.get(i).setVisibleFalse();
                        visible=false;
                    }
                }
            }
        }
    }

    @Override
    public void draw() {
        if(visible) {
            Drawer.draw(x, y, Assets.firstGoblin, enemyWidth, enemyHeight);
            for(int i=0;i<listOfProjectiles.size();i++){
                    listOfProjectiles.get(i).draw();
            }
        }
    }

}
