package com.example.sauronsarmy.oopp.upgrades;


import android.content.Context;

import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

public interface HomeMVPInterface {

    interface Presenter{
        Upgrade getOilPumpUpgrade();
        boolean buyOilPumpUpgrade();
        int getPlayerMoneyPerSec();
        int getPlayerMoney();
        void loadState(Context context);
        void saveState(Context context);
        int getOilPumpUpgradeCounter();

        //for testing
        void setOilCounter(int counter);
        void testOilPumpUpgradeCounter(Map<String, Integer> map);
      
    }

    interface Model {
        Upgrade getOilPumpUpgrade();
        Map getUpgradeCounters();
        boolean buyOilPumpUpgrade();
        void setOilPumpUpgradeCounter(Map<String, Integer> map);
        int getPlayerMoneyPerSec();
        int getPlayerMoney();
        int getOilPumpUpgradeCounter();
        void loadState(Context context);
        void saveState(Context context);

        //for testing
        void setOilCounter(int counter);
        void testOilPumpUpgradeCounter(Map<String, Integer> map);
      
    }

}
