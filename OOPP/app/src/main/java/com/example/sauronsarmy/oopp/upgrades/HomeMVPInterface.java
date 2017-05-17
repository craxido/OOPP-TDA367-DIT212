package com.example.sauronsarmy.oopp.upgrades;


import android.content.Context;

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
    }

    interface Model {
        Upgrade getOilPumpUpgrade();
        boolean buyOilPumpUpgrade();
        int getPlayerMoneyPerSec();
        int getPlayerMoney();
        int getOilPumpUpgradeCounter();
        void loadState(Context context);
        void saveState(Context context);
    }

}
