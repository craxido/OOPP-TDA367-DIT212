package com.example.sauronsarmy.oopp.stats;

import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

/**
 * Created by Erik on 13/04/17.
 */

public class StatsPresenter implements StatsPresenterInterface {

    private PlayerModelInterface playerModel;

    StatsPresenter() {
        playerModel = PlayerModel.getInstance();
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
