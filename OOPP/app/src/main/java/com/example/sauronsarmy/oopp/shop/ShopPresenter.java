package com.example.sauronsarmy.oopp.shop;

import android.content.Context;

import com.example.sauronsarmy.oopp.map.MapMVPInterface;
import com.example.sauronsarmy.oopp.map.MapPresenter;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.upgrade.Upgrade;

/**
 * Created by Sarosh on 2017-05-11.
 * @Author Sarosh
 */

public class ShopPresenter implements ShopMVPInterface.Presenter {

    private ShopMVPInterface.Model shopModel;
    private PlayerModelInterface playerModel;
    private MapMVPInterface.PresenterOps mapPresenter;

    public ShopPresenter() {
        shopModel = Shop.getInstance();
        playerModel = PlayerModel.getInstance();
        mapPresenter = new MapPresenter();
    }

    @Override
    public boolean buyDamageUpgrade() {
        return shopModel.buyDamageUpgrade(playerModel);
    }

    @Override
    public boolean buyDPSUpgrade() {
        return shopModel.buyDPSUpgrade(playerModel);
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
        playerModel.saveState(context);
    }

    @Override
    public void loadState(Context context) {
        shopModel.loadState(context);
    }

    @Override
    public int getPlayerMoney(){
        return playerModel.getMoney();
    }

    @Override
    public int getPlayerDamage() {
        return playerModel.getDamage();
    }

    @Override
    public int getPlayerDamagePerSecond() {
        return playerModel.getDamagePerSecond();
    }

    @Override
    public void update() {
        mapPresenter.applyDPS();
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

