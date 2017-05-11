package com.example.sauronsarmy.oopp.Upgrades;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

class HomePresenter implements HomeMVPInterface.Presenter {

    private HomeMVPInterface.Model homeModel;

    HomePresenter() {
        homeModel = Home.getInstance();
    }

    @Override
    public Upgrade getOilPumpUpgrade() {
        return homeModel.getOilPumpUpgrade();
    }

    @Override
    public int getOilPumpUpgradeCounter() {
        return homeModel.getOilPumpUpgradeCounter();
    }

    @Override
    public void setOilPumpUpgradeCounter(HashMap<String, Integer> map) {
        homeModel.setOilPumpUpgradeCounter(map);
    }

    @Override
    public Map getUpgradeCounters() {
        return homeModel.getUpgradeCounters();
    }

    @Override
    public boolean buyOilPumpUpgrade() {
        return homeModel.buyOilPumpUpgrade();
    }
}
