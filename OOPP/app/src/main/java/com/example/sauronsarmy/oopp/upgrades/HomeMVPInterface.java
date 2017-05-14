package com.example.sauronsarmy.oopp.upgrades;


import android.content.Context;

import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

public interface HomeMVPInterface {

    interface Presenter{
        Upgrade getOilPumpUpgrade();
        int getOilPumpUpgradeCounter();
        void setOilPumpUpgradeCounter(Map<String, Integer> map);
        Map getUpgradeCounters();
        boolean buyOilPumpUpgrade();
        void saveState(Context context);
    }

    interface Model {
        Upgrade getOilPumpUpgrade();
        int getOilPumpUpgradeCounter();
        void setOilPumpUpgradeCounter(Map<String, Integer> map);
        Map getUpgradeCounters();
        boolean buyOilPumpUpgrade();
    }

}
