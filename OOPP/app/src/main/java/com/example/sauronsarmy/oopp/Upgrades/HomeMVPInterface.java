package com.example.sauronsarmy.oopp.Upgrades;


import android.content.Context;

import java.util.HashMap;
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
        int getPlayerMoneyPerSec();
        int getPlayerMoney();
      
        //for testing
        void setOilCounter(int counter);
        void testOilPumpUpgradeCounter(Map<String, Integer> map);
      
    }

    interface Model {
        Upgrade getOilPumpUpgrade();
        int getOilPumpUpgradeCounter();
        Map getUpgradeCounters();
        boolean buyOilPumpUpgrade();
        void setOilPumpUpgradeCounter(Map<String, Integer> map);
        int getPlayerMoneyPerSec();
        int getPlayerMoney();
      
        //for testing
        void setOilCounter(int counter);
        void testOilPumpUpgradeCounter(Map<String, Integer> map);
      
    }

}
