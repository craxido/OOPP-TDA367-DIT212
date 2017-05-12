package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.Player.PlayerModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by erik on 18/04/17.
 */
public class PlayerModelTest {
    private PlayerModel player;
    private static final double DELTA = 1e-15;

    /*
    At setup the player will have the stats:
        damage          = 10;
        damageMultiplier = 1;
        money           = 0;
        moneyPerSecond  = 0;
     */
    @Before
    public void setUp() throws Exception {
        player = PlayerModel.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        player = null;
    }

    @Test
    public void getDamageMultiplier() throws Exception {
        assertEquals(1.0, player.getDamagePerSecond(), DELTA);
    }

    @Test
    public void setDamageMultiplier() throws Exception {
        player.setDamagePerSecond(3);
        assertEquals(3, player.getDamagePerSecond(), DELTA);
        player.setDamagePerSecond(1);
    }

    @Test
    public void getDamage() throws Exception {
        assertEquals(10, player.getDamage());
    }

    @Test
    public void setDamage() throws Exception {
        player.setDamage(50);
        assertEquals(50, player.getDamage());
        player.setDamage(10);
    }

    @Test
    public void getMoney() throws Exception {
        assertEquals(0, player.getMoney());
    }

    @Test
    public void setMoney() throws Exception {
        player.setMoney(40);
        assertEquals(40, player.getMoney());
        player.setMoney(0);
    }

    @Test
    public void getMoneyPerSecond() throws Exception {
        assertEquals(0, player.getMoneyPerSecond());
    }

    @Test
    public void setMoneyPerSecond() throws Exception {
        player.setMoneyPerSecond(35);
        assertEquals(35.0, player.getMoneyPerSecond(),DELTA);
        player.setMoneyPerSecond(0);
    }

}