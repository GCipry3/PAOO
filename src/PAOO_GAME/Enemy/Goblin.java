package PAOO_GAME.Enemy;

import PAOO_GAME.Component.Drawer;

import java.util.Random;

import static PAOO_GAME.Constants.enemyHeight;
import static PAOO_GAME.Constants.enemyWidth;
import static PAOO_GAME.Graphics.Assets.goblinLeft;
import static PAOO_GAME.Graphics.Assets.goblinRight;

public class Goblin extends Enemy{
    private int cntAttack=0;
    private boolean attackOnlyOnce = true;
    protected int random;

    public Goblin(int x, int y) {
        super(x, y);
        damage=10;
        random= new Random().nextInt(0,3);
    }

    @Override
    protected void attack() {
        if(playerClose()){
            if(cntAttack==0){
                createNewProjectile();
                cntAttack=1;
            }
            if(cntAttack>0&&cntAttack<60){
                cntAttack++;
            }
            if(cntAttack>=60){
                cntAttack=0;
            }

            if(attackOnlyOnce) {
                if (collisionWithPlayerCheck()) {
                    giveDamageToPlayer(100);
                    attackOnlyOnce=false;
                }
            }
        }
        attackProjectiles();
    }

    @Override
    public void update() {
        if(visible) {

            if (playerClose()) {
                move();
            }

            if (playerAttackStatusCheck())//if player attacks
            {
                if(collisionWithAttackStateCheck()){
                    visible=false;
                }
            }

            if(collisionWithShurikensCheck()) visible=false;

            if(visible) attack();
        }

        updateProjectiles();
    }

    @Override
    public void draw() {
        if(visible) {
            if(state==1){
                Drawer.draw(x, y, goblinRight.get(random), enemyWidth, enemyHeight);
            }else{
                Drawer.draw(x, y, goblinLeft.get(random), enemyWidth, enemyHeight);
            }
            drawProjectiles();
        }
    }

}
