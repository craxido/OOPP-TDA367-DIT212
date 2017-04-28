package com.example.sauronsarmy.oopp.Stats;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

/**
 * Created by Erik on 13/04/17.
 */

public class StatsPresenter implements StatsPresenterInterface {

    private PlayerModelInterface playerModel;

    StatsPresenter() {
        playerModel = PlayerModel.getInstance();
    }

    @Override
    public double getMoneyPerSecond() {
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
    public double getPlayerDamageMultiplier() {
        return playerModel.getDamageMultiplier();
    }
}
