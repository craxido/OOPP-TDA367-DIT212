package com.example.sauronsarmy.oopp.stats;

import android.content.Context;

import com.example.sauronsarmy.oopp.map.MapMVPInterface;
import com.example.sauronsarmy.oopp.map.MapPresenter;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

/**
 * Created by Erik on 13/04/17.
 */

class StatsPresenter implements StatsInterface.Presenter {

    private PlayerModelInterface playerModel;
    private MapMVPInterface.PresenterOps mapPresenter;

    StatsPresenter() {
        playerModel = PlayerModel.getInstance();
        mapPresenter = new MapPresenter();
    }

    @Override
    public void saveState(Context context) {
        playerModel.saveState(context);
    }

    @Override
    public void update() {
        mapPresenter.applyDPS();
    }

    @Override
    public int getMonstersKilled() {
        return playerModel.getMonstersKilled();
    }

    @Override
    public int getMoneyPerSecond() {
        return playerModel.getMoneyPerSecond();
    }

    @Override
    public int getMoneyAmount() {
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
}
