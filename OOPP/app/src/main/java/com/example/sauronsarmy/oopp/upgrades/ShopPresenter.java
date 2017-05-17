package com.example.sauronsarmy.oopp.upgrades;

import android.content.Context;

import com.example.sauronsarmy.oopp.MainMVPInterface;
import com.example.sauronsarmy.oopp.MainPresenter;

/**
 * Created by Sarosh on 2017-05-11.
 */

public class ShopPresenter implements ShopMVPInterface.Presenter {

    private ShopMVPInterface.Model shopModel;
    private final static MainMVPInterface.PresenterOps mainPresenter = new MainPresenter();

    public ShopPresenter() {
        shopModel = Shop.getInstance();
        //mainModel = mainModel.getInstance();
        // Hey there circular dependency
        //mainPresenter = new MainPresenter();
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

