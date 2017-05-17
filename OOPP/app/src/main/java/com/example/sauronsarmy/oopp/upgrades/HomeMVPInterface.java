package com.example.sauronsarmy.oopp.upgrades;


import android.content.Context;

import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

interface HomeMVPInterface {

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
        boolean buyOilPumpUpgrade(PlayerModelInterface player);
        void setOilPumpUpgradeCounter(Map<String, Integer> map);
        int getOilPumpUpgradeCounter();
        void loadState(Context context);
        void saveState(Context context);

        //for testing
        void setOilCounter(int counter);
        void testOilPumpUpgradeCounter(Map<String, Integer> map);
      
    }

}
