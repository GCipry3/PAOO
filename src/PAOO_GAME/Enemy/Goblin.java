package PAOO_GAME.Enemy;

import PAOO_GAME.Collisions.Collision;
import PAOO_GAME.Component.Drawer;
import PAOO_GAME.Graphics.Assets;
import PAOO_GAME.Player.Player;
import PAOO_GAME.Player.ShinobiShuriken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static PAOO_GAME.Constants.*;
import static PAOO_GAME.Game.player;

public class Goblin extends Enemy{
    protected List<Projectile> listOfProjectiles= new ArrayList<>();
    protected int damage=10;
    private int cntAttack=0;
    private boolean attackOnlyOnce = true;
    protected int random;

    public Goblin(int x, int y) {
        super(x, y);
        random= new Random().nextInt(0,3);
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

            if(attackOnlyOnce) {
                if (Collision.checkCollision(
                        x,
                        y,
                        enemyWidth,
                        enemyHeight,
                        xPlayer,
                        yPlayer,
                        playerWidth,
                        playerHeight)
                ) {
                    player.takeDamage(100);
                    attackOnlyOnce=false;
                }
            }
        }

        listOfProjectiles.forEach(Projectile::attack);
    }

    @Override
    public void update() {
        if(visible) {

            int xPlayer=player.getX();
            int yPlayer=player.getY();
            if (playerClose()) {
                move();
            }

            if (Player.getAttackStatus())//if player attacks
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
                            tmp.getProjectileWidth(),
                            tmp.getProjectileHeight()
                    ))
                    {
                        player.listOfShurikens.get(i).setVisibleFalse();
                        visible=false;
                    }
                }
            }
            if(visible)attack();
        }

        listOfProjectiles.forEach(Projectile::update);
    }

    @Override
    public void draw() {
        if(visible) {
            switch (random) {
                case 0 -> Drawer.draw(x, y, Assets.firstGoblin, enemyWidth, enemyHeight);
                case 1 -> Drawer.draw(x, y, Assets.secondGoblin, enemyWidth, enemyHeight);
                case 2 -> Drawer.draw(x, y, Assets.thirdGoblin, enemyWidth, enemyHeight);
            }
            listOfProjectiles.forEach(Projectile::draw);
        }
    }

}
