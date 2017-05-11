package com.example.sauronsarmy.oopp.Upgrades;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

public class ShopPresenter implements ShopMVPInterface.Presenter {

    private ShopMVPInterface.Model shopModel;

    ShopPresenter() {
        shopModel = Shop.getInstance();
    }

    @Override
    public boolean buyDamageUpgrade() {
        return shopModel.buyDamageUpgrade();
    }

    @Override
    public boolean buyMultiplierUpgrade() {
        return shopModel.buyMultiplierUpgrade();
    }

    @Override
    public Upgrade getDamageUpgrade() {
        return shopModel.getDamageUpgrade();
    }

    @Override
    public Upgrade getMultiplierUpgrade() {
        return shopModel.getMultiplierUpgrade();
    }

    @Override
    public int getDamageUpgradeCounter() {
        return shopModel.getDamageUpgradeCounter();
    }

    @Override
    public int getMultiplierUpgradeCounter() {
        return shopModel.getMultiplierUpgradeCounter();
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
