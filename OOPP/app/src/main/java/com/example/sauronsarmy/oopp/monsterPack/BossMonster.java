package com.example.sauronsarmy.oopp.monsterPack;

import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

/**
 * @author Jonatan Källman
 */

public class BossMonster extends Monster implements ClockListener {

    private int time;
    private int timeLimit;

    public BossMonster(int health, int gold, int imageref){
        super(health,gold,imageref);
        Runner.getInstance().register(this);
        time = 0;
        timeLimit = 20;
    }

    public void resetHealth(){
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

    public int getTime(){
        return time;
    }

    public void setTime(int time){
        this.time=time;
    }

}
