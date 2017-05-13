package com.example.sauronsarmy.oopp.Upgrades;

import android.content.Context;

import com.example.sauronsarmy.oopp.MainPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 */

public class HomePresenter implements HomeMVPInterface.Presenter {

    private HomeMVPInterface.Model homeModel;
    private MainPresenter mainPresenter;

    public HomePresenter() {
        homeModel = Home.getInstance();
        // Hey there circular dependency
        mainPresenter = MainPresenter.getInstance();
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
    public void setOilPumpUpgradeCounter(Map<String, Integer> map) {
        homeModel.setOilPumpUpgradeCounter(map);
    }

    @Override
    public void setOilCounter(int i){
        homeModel.setOilCounter(i);
    }

    public void saveState(Context context) {
        mainPresenter.saveState(context);
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
