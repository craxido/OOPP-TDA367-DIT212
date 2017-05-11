package com.example.sauronsarmy.oopp.Upgrades;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

public interface ShopMVPInterface {

    interface Presenter {
        boolean buyDamageUpgrade();
        boolean buyMultiplierUpgrade();
        Upgrade getDamageUpgrade();
        Upgrade getMultiplierUpgrade();
        int getDamageUpgradeCounter();
        int getMultiplierUpgradeCounter();
        Map getUpgradeCounters();
        void setUpgradeCounters(HashMap<String, Integer> map);
    }

    interface Model {
        boolean buyDamageUpgrade();
        boolean buyMultiplierUpgrade();
        Upgrade getDamageUpgrade();
        Upgrade getMultiplierUpgrade();
        int getDamageUpgradeCounter();
        int getMultiplierUpgradeCounter();
        Map getUpgradeCounters();
        void setUpgradeCounters(HashMap<String, Integer> map);
    }
}
