package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.shop.ShopMVPInterface;
import com.example.sauronsarmy.oopp.shop.ShopPresenter;
import com.example.sauronsarmy.oopp.upgrade.Upgrade;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sarosh on 2017-05-23.
 * @Author Sarosh
 */

public class ShopTest {
    private PlayerModelInterface player;
    private ShopPresenter shop;

    /**
     * Initial stats for shop:
     *
     * Damage upgrade counter: 1
     * Damage upgrade stat: 2
     * Damage upgrade cost: 50
     *
     * Dps upgrade counter: 1
     * Dps upgrade stat: 1
     * Dps upgrade cost: 100
     */

    @Before
    public void setUp(){
        shop = new ShopPresenter();
    }

    @After
    public void tearDown(){
        shop = null;
    }

    /**
     *  Checks that nothing is null
     * */

    @Test
    public void exists(){
        assertNotNull(shop);
        assertNotNull(shop.getDamageUpgrade());
        assertNotNull(shop.getDPSUpgrade());
    }

    @Test
    public void testValues(){
        assertEquals(1, shop.getDamageUpgradeCounter());
        assertEquals(1, shop.getDPSUpgradeCounter());
        Upgrade damageUpgrade = shop.getDamageUpgrade();
        Upgrade dpsUpgrade = shop.getDPSUpgrade();

        assertEquals(2, damageUpgrade.getStat());
        assertEquals(50, damageUpgrade.getCost());

        assertEquals(1, dpsUpgrade.getStat());
        assertEquals(100, dpsUpgrade.getCost());
    }
}
