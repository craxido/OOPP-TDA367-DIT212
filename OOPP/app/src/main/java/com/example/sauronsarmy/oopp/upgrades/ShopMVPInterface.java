package com.example.sauronsarmy.oopp.upgrades;

import android.content.Context;


/**
 * Created by Sarosh on 2017-05-11.
 */

public interface ShopMVPInterface {

    interface Presenter {
        boolean buyDamageUpgrade();
        boolean buyDPSUpgrade();
        Upgrade getDamageUpgrade();
        Upgrade getDPSUpgrade();
        int getPlayerMoney();
        int getDamageUpgradeCounter();
        int getDPSUpgradeCounter();
        void saveState(Context context);
        void loadState(Context context);
    }

    interface Model {
        boolean buyDamageUpgrade();
        boolean buyDPSUpgrade();
        int getDPSUpgradeCounter();
        int getDamageUpgradeCounter();
        Upgrade getDamageUpgrade();
        Upgrade getDPSUpgrade();
        int getPlayerMoney();
        void saveState(Context context);
        void loadState(Context context);
    }
}
