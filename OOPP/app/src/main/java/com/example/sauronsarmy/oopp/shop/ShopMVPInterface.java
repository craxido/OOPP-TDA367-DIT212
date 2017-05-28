package com.example.sauronsarmy.oopp.shop;

import android.content.Context;

import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.upgrade.Upgrade;


/**
 * Created by Sarosh on 2017-05-11.
 * @Author Sarosh
 */

public interface ShopMVPInterface {

    interface Presenter {
        boolean buyDamageUpgrade();
        boolean buyDPSUpgrade();
        Upgrade getDamageUpgrade();
        Upgrade getDPSUpgrade();
        int getDamageUpgradeCounter();
        int getDPSUpgradeCounter();
        void saveState(Context context);
        void loadState(Context context);
        int getPlayerMoney();
        int getPlayerDamage();
        int getPlayerDamagePerSecond();
        void update();
    }

    interface Model {
        boolean buyDamageUpgrade(PlayerModelInterface player);
        boolean buyDPSUpgrade(PlayerModelInterface player);
        int getDPSUpgradeCounter();
        int getDamageUpgradeCounter();
        Upgrade getDamageUpgrade();
        Upgrade getDPSUpgrade();
        void saveState(Context context);
        void loadState(Context context);
    }
}
