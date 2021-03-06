package com.example.sauronsarmy.oopp.home;

import android.content.Context;

import com.example.sauronsarmy.oopp.map.MapMVPInterface;
import com.example.sauronsarmy.oopp.map.MapPresenter;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.upgrade.Upgrade;

import java.util.Map;

/**
 * Created by Sarosh on 2017-05-11.
 * @Author Sarosh
 */

public class HomePresenter implements HomeMVPInterface.Presenter {

    private HomeMVPInterface.Model homeModel;
    private PlayerModelInterface playerModel;
    private MapMVPInterface.PresenterOps mapPresenter;
    public HomePresenter() {
        homeModel = Home.getInstance();
        playerModel = PlayerModel.getInstance();
        mapPresenter = new MapPresenter();
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
    public void update() {
        mapPresenter.applyDPS();
    }

    @Override
    public void setOilCounter(int i){
        homeModel.setOilCounter(i);
    }

    @Override
    public void testOilPumpUpgradeCounter(Map<String, Integer> map) {
        homeModel.testOilPumpUpgradeCounter(map);
    }
    public int getPlayerMoneyPerSec(){
        return playerModel.getMoneyPerSecond();
    }

    public void saveState(Context context) {
        homeModel.saveState(context.getApplicationContext());
        playerModel.saveState(context.getApplicationContext());
    }

    @Override
    public boolean buyOilPumpUpgrade() {
        return homeModel.buyOilPumpUpgrade(playerModel);
    }

    @Override
    public int getPlayerMoney(){
        return playerModel.getMoney();
    }

    @Override
    public void loadState(Context context) {
        homeModel.loadState(context.getApplicationContext());
    }
}
