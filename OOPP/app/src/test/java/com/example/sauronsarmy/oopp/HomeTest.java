package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Upgrades.HomeMVPInterface;
import com.example.sauronsarmy.oopp.Upgrades.HomePresenter;

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
    private PlayerModel player;
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
        player = PlayerModel.getInstance();
        home = new HomePresenter();
    }

    @After
    public void tearDown(){
        player = null;
        home = null;
    }

    @Test
    public void exists(){
        assertNotNull(home);
        assertNotNull(player);
        assertNotNull(home.getOilPumpUpgrade());
    }

    @Test
    public void homeGetSetCounter(){
        assertEquals(1, home.getOilPumpUpgradeCounter());
        home.setOilCounter(10);
        assertEquals(10, home.getOilPumpUpgradeCounter());
        Map map2 = home.getUpgradeCounters();
        assertEquals(10,map2.get("oil"));
    }

    @Test
    public void playerBuysUpgrade(){
        // check player init stats and set money
        assertEquals(0, player.getMoney());
        assertEquals(0, player.getMoneyPerSecond());
        player.setMoney(100);
        assertEquals(100, player.getMoney());
        // check upgrade init stats
        assertEquals(100, home.getOilPumpUpgrade().getCost());
        assertEquals(1, home.getOilPumpUpgrade().getStat());
        assertEquals(1, home.getOilPumpUpgradeCounter());
        //buy upgrade
        home.buyOilPumpUpgrade();
        /* check if bought */
        // check home
        assertEquals(2, home.getOilPumpUpgradeCounter());
        assertNotEquals(100, home.getOilPumpUpgrade().getCost());
        assertNotEquals(1, home.getOilPumpUpgrade().getStat());
        // check player
        assertEquals(0, player.getMoney());
        assertEquals(1, player.getMoneyPerSecond());

    }



}
