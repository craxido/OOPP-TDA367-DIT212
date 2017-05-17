package com.example.sauronsarmy.oopp.upgrades;

import android.content.Context;

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
    public void saveState(Context context) {
        shopModel.saveState(context);
    }

    @Override
    public void loadState(Context context) {
        shopModel.loadState(context);
    }

    @Override
    public int getPlayerMoney(){
        return shopModel.getPlayerMoney();
    }

    @Override
    public int getDamageUpgradeCounter() {
        return shopModel.getDamageUpgradeCounter();
    }

    @Override
    public int getDPSUpgradeCounter() {
        return shopModel.getDPSUpgradeCounter();
    }
}

