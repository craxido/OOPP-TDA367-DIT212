package com.example.sauronsarmy.oopp.Shop;

import android.content.Context;

import com.example.sauronsarmy.oopp.Upgrade.Upgrade;

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
        int getPlayerMoney();
        Map getUpgradeCounters();
        void setUpgradeCounters(Map<String, Integer> map);
        void saveState(Context context);
    }

    interface Model {
        boolean buyDamageUpgrade();
        boolean buyDPSUpgrade();
        Upgrade getDamageUpgrade();
        Upgrade getDPSUpgrade();
        int getDamageUpgradeCounter();
        int getDPSUpgradeCounter();
        int getPlayerMoney();
        Map getUpgradeCounters();
        void setUpgradeCounters(Map<String, Integer> map);

    }
}
