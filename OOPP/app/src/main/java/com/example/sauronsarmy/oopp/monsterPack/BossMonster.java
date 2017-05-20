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
        timeLimit = 20;

    }

    private void resetHealth(){
        setHealth(getMaxHealth());
    }

    @Override
    public void update(){
        time++;
        if (time > timeLimit){
            resetHealth();
            time=0;
        }
    }

    @Override
    public void setHealth(int health){
        super.setHealth(health);
    }

    @Override
    public int getMaxHealth(){
        return super.getMaxHealth();
    }

    @Override
    public boolean isBoss(){
        return true;
    }

}
