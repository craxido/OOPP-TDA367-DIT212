package com.example.sauronsarmy.oopp.Upgrades;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

interface HomeMVPInterface {

    interface Presenter{
        Upgrade getOilPumpUpgrade();
        int getOilPumpUpgradeCounter();
        void setOilPumpUpgradeCounter(HashMap<String, Integer> map);
        Map getUpgradeCounters();
        boolean buyOilPumpUpgrade();
    }

    interface Model {
        Upgrade getOilPumpUpgrade();
        int getOilPumpUpgradeCounter();
        void setOilPumpUpgradeCounter(HashMap<String, Integer> map);
        Map getUpgradeCounters();
        boolean buyOilPumpUpgrade();
    }

}
