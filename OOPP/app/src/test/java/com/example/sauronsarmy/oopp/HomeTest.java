package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
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

    @Before
    void setUp(){
        player = PlayerModel.getInstance();
        home = new HomePresenter();
    }

    @After
    void tearDown(){
        player = null;
        home = null;
    }

    @Test
    void exists(){
        assertNotNull(home);
        assertNotNull(player);
        assertNotNull(home.getOilPumpUpgrade());
    }

    @Test
    void homeGetSetCounter(){
        assertEquals(1, home.getOilPumpUpgradeCounter());
        HashMap<String, Integer> map = new HashMap<>();
        map.put("oil", 10);
        home.setOilPumpUpgradeCounter(map);
        assertEquals(10, home.getOilPumpUpgradeCounter());
        Map map2 = home.getUpgradeCounters();
        assertEquals(10,map2.get("oil"));

    }



}
