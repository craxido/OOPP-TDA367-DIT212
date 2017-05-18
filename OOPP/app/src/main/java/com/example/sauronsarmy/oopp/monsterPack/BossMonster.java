package com.example.sauronsarmy.oopp.monsterPack;

/**
 * @Author: Jonatan on 18/05/2017.
 */

public class BossMonster extends Monster {

    public BossMonster(int health, int gold, int imageref){
        super(health,gold,imageref);
    }

    private void resetHealth(){
        super.setHealth(super.getMaxHealth());
    }

}
