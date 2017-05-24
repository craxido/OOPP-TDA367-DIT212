package com.example.sauronsarmy.oopp.home;


import android.content.Context;

import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.upgrade.Upgrade;

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
        void update();

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
