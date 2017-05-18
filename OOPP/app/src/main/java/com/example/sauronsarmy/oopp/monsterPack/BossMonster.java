package com.example.sauronsarmy.oopp.monsterPack;

import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

/**
 * @Author: Jonatan Källman
 */

public class BossMonster extends Monster implements ClockListener {

    private Runner bossTimer;
    private int time;
    private int timeLimit;

    public BossMonster(int health, int gold, int imageref){
        super(health,gold,imageref);
        bossTimer = Runner.getInstance();
        time = 0;
        timeLimit = 30;
    }

    private void resetHealth(){
        super.setHealth(super.getMaxHealth());
    }

    @Override
    public void update(){
        time++;
        if (time > timeLimit){
            this.resetHealth();
        }
    }
}
