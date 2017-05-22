package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.map.areaType;
import com.example.sauronsarmy.oopp.monsterPack.BossMonster;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;

import android.content.Context;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;

/**
 * @author Jonatan Källman
 */

@RunWith(MockitoJUnitRunner.class)
public class BossMonsterTest {

    private BossMonster boss = mock (BossMonster.class);
    private monsterFactory monfac  =mock (monsterFactory.class);

    @Mock
    private Context mMockContext;

    @Before
    public void setUp() throws Exception {
        when(monfac.getBossMonster(10000, 10000, areaType.FOREST)).thenReturn(boss);
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
        assert (boss.getTime() == 1);
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
