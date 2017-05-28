package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.home.HomePresenter;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Sarosh on 2017-05-13.
 */

public class HomeTest {
    private PlayerModelInterface player;
    private HomePresenter home;

    /**
     * Initial stats for player:
     * Damage: 10
     * DPS: 0
     * MPS: 0
     * Money: 0
     *
     * Initial stats for home:
     * Oil upgrade counter: 1
     * Oil upgrade stat: 1
     * Oil upgrade cost: 100
     */


    @Before
    public void setUp(){
        home = new HomePresenter();
        player = PlayerModel.getInstance();
    }

    @After
    public void tearDown(){
        player = null;
        home = null;
    }

    /**
     *  Checks that nothing is null
     * */
    @Test
    public void exists(){
        assertNotNull(home);
        assertNotNull(player);
        assertNotNull(home.getOilPumpUpgrade());
    }

    /**
     *  Tests the set and get on the upgrade counter. 
     *  Should always start at 1
     * */
    @Test
    public void homeGetSetCounter(){
        assertEquals(1, home.getOilPumpUpgradeCounter());
        home.setOilCounter(5);
        assertEquals(5, home.getOilPumpUpgradeCounter());
    }

    /**
     *  Tests on player:
     *  |- getMoney();
     *  |- getMoneyPerSecond();
     *  |- setMoney();
     *  |- setMoneyPerSecond();
     *
     *  Tests on home:
     *  |- getOilPumpUpgrade();
     *  |- getOilPumpUpgradeCounter();
     *  |- buyOilPumpUpgrade();
     *
     *  Tests on upgrade:
     *  getCost();
     *
     *  stats not checked since they are subject to change
     * */
    @Test
    public void playerBuysUpgrade(){
        // check player init stats and set money
        assertEquals(0, player.getMoney());
        assertEquals(0, player.getMoneyPerSecond());
        player.addMoney(100);
        assertEquals(100, player.getMoney());
        // check upgrade init stats
        assertEquals(100, home.getOilPumpUpgrade().getCost());
        assertEquals(1, home.getOilPumpUpgradeCounter());
        //buy upgrade
        assertTrue(home.buyOilPumpUpgrade());
        /* check if bought */
        // check home
        assertEquals(2, home.getOilPumpUpgradeCounter());
        assertNotEquals(100, home.getOilPumpUpgrade().getCost());
        // check player
        assertEquals(0, player.getMoney());
        assertEquals(1, player.getMoneyPerSecond());

    }

    @Test
    public void poorPlayerBuysUpgrade(){
        // check player init stats, make sure 0
        assertEquals(0, player.getMoney());

        //try to buy upgrade
        assertFalse(home.buyOilPumpUpgrade());
    }




}
