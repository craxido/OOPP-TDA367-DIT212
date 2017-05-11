package com.example.sauronsarmy.oopp.Upgrades;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

class ShopPresenter implements ShopMVPInterface.Presenter {

    private ShopMVPInterface.Model shopModel;

    ShopPresenter() {
        shopModel = Shop.getInstance();
    }

    @Override
    public boolean buyDamageUpgrade() {
        return shopModel.buyDamageUpgrade();
    }

    @Override
    public boolean buyDPSUpgrade() {
        return shopModel.buyDPSUpgrade();
    }

    @Override
    public Upgrade getDamageUpgrade() {
        return shopModel.getDamageUpgrade();
    }

    @Override
    public Upgrade getDPSUpgrade() {
        return shopModel.getDPSUpgrade();
    }

    @Override
    public int getDamageUpgradeCounter() {
        return shopModel.getDamageUpgradeCounter();
    }

    @Override
    public int getDPSUpgradeCounter() {
        return shopModel.getDPSUpgradeCounter();
    }

    @Override
    public Map getUpgradeCounters() {
        return shopModel.getUpgradeCounters();
    }

    @Override
    public void setUpgradeCounters(HashMap<String, Integer> map) {
        shopModel.setUpgradeCounters(map);
    }
}
