package com.example.sauronsarmy.oopp.monsterPack;

import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

/**
 * @Author: Jonatan on 18/05/2017.
 */

public class BossMonster extends Monster implements ClockListener {

    private Runner bossTimer;
    private int time;

    public BossMonster(int health, int gold, int imageref){
        super(health,gold,imageref);
        bossTimer=Runner.getInstance();
        time=0;
    }

    private void resetHealth(){
        super.setHealth(super.getMaxHealth());
    }

    @Override
    public void update(){
        time++;
        if (time>30){
            this.resetHealth();
        }
    }
}
