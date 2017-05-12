package com.example.sauronsarmy.oopp.Upgrades;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

public interface ShopMVPInterface {

    interface Presenter {
        boolean buyDamageUpgrade();
        boolean buyDPSUpgrade();
        Upgrade getDamageUpgrade();
        Upgrade getDPSUpgrade();
        int getDamageUpgradeCounter();
        int getDPSUpgradeCounter();
        Map getUpgradeCounters();
        void setUpgradeCounters(Map<String, Integer> map);
    }

    interface Model {
        boolean buyDamageUpgrade();
        boolean buyDPSUpgrade();
        Upgrade getDamageUpgrade();
        Upgrade getDPSUpgrade();
        int getDamageUpgradeCounter();
        int getDPSUpgradeCounter();
        Map getUpgradeCounters();
        void setUpgradeCounters(Map<String, Integer> map);
    }
}
