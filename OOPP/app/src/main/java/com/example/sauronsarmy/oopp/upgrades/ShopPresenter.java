package com.example.sauronsarmy.oopp.upgrades;

import android.content.Context;

import com.example.sauronsarmy.oopp.MainMVPInterface;
import com.example.sauronsarmy.oopp.MainPresenter;

import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

public class ShopPresenter implements ShopMVPInterface.Presenter {

    private ShopMVPInterface.Model shopModel;
    private MainMVPInterface.PresenterOps mainPresenter;

    public ShopPresenter() {
        shopModel = Shop.getInstance();
        // Hey there circular dependency
        mainPresenter = new MainPresenter();
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
    public void setUpgradeCounters(Map<String, Integer> map) {
        shopModel.setUpgradeCounters(map);
    }

    @Override
    public void saveState(Context context) {
        mainPresenter.saveState(context);
    }
}

