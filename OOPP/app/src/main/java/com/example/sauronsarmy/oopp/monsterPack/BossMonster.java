package com.example.sauronsarmy.oopp.monsterPack;

import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

/**
 * @author Jonatan Källman
 */

public class BossMonster extends Monster implements ClockListener {

    private int time;
    private int timeLimit;

    BossMonster(int health, int gold, int imageref){
        super(health,gold,imageref,true);
        Runner.getInstance().register(this);
        timeLimit = 20;
        time = timeLimit;
    }

    public void resetHealth(){
        setHealth(getMaxHealth());
    }

    private void resetTime() {
        time = timeLimit;
    }

    private void resetBoss() {
        resetHealth();
        resetTime();
    }

    @Override
    public void update(){
        time--;
        if (time == 0){
            resetBoss();
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

    public int getTime(){
        return time;
    }

    public int getTimeLimit(){
        return timeLimit;
    }

    public void setTime(int time){
        this.time=time;
    }

}
