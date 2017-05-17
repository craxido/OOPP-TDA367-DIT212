package com.example.sauronsarmy.oopp.upgrades;

import android.content.Context;

/**
 * Created by Sarosh on 2017-05-11.
 */

public class HomePresenter implements HomeMVPInterface.Presenter {

    private HomeMVPInterface.Model homeModel;
    public HomePresenter() {
        homeModel = Home.getInstance();
    }

    @Override
    public Upgrade getOilPumpUpgrade() {
        return homeModel.getOilPumpUpgrade();
    }

    @Override
    public int getPlayerMoneyPerSec(){
        return homeModel.getPlayerMoneyPerSec();
    }

    public void saveState(Context context) {
        homeModel.saveState(context);
    }

    @Override
    public int getOilPumpUpgradeCounter() {
        return homeModel.getOilPumpUpgradeCounter();
    }

    @Override
    public boolean buyOilPumpUpgrade() {
        return homeModel.buyOilPumpUpgrade();
    }

    @Override
    public int getPlayerMoney(){
        return homeModel.getPlayerMoney();
    }

    @Override
    public void loadState(Context context) {
        homeModel.loadState(context);
    }
}
