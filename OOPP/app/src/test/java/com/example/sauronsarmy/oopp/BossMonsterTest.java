package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.monsterPack.BossMonster;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class BossMonsterTest {

    private BossMonster boss;

    @Before
    public void setUp() throws Exception {
        boss = new BossMonster(10000, 10000, R.drawable.boss_monster);
    }

    @After
    public void tearDown() throws Exception {
        boss = null;
    }

    @Test
    public void testHealthReset(){
        boss.damageMonster(1);
        boss.resetHealth();
        assert(boss.getMaxHealth() == boss.getHealth());
    }

    @Test
    public void testTime(){
        boss.setTime(1);
        assert (boss.getTime()==1);
    }

}
