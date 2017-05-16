package com.example.sauronsarmy.oopp.stats;

import com.example.sauronsarmy.oopp.MainMVPInterface;
import com.example.sauronsarmy.oopp.MainPresenter;
import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

/**
 * Created by Erik on 13/04/17.
 */

public class StatsPresenter implements StatsInterface.Presenter {

    private PlayerModelInterface playerModel;
    private MainMVPInterface.PresenterOps mainPresenter;

    StatsPresenter() {
        playerModel = PlayerModel.getInstance();
        mainPresenter = new MainPresenter();
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

    @Override
    public Runner getRun() {
        return mainPresenter.getRun();
    }
}
