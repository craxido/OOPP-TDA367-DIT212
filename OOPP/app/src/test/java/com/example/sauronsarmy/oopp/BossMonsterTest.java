package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.monsterPack.BossMonster;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * @author Jonatan Källman
 */

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

    @Test
    public void testUpdate(){
        boss.setTime(20);         //Set time so that boss should reset
        boss.damageMonster(1);    //Damage monster so that update affects health
        int healthBeforeUpdate = boss.getHealth();
        boss.update();
        assert (!(healthBeforeUpdate == boss.getHealth()));
        assert (boss.getTime() == 0);
    }
}
