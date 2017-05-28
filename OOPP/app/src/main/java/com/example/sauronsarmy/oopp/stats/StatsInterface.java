package com.example.sauronsarmy.oopp.stats;

import android.content.Context;

import com.example.sauronsarmy.oopp.clock.Runner;

/**
 * Created by erik on 16/05/17.
 */

public interface StatsInterface {

    interface Presenter {
        int getPlayerDamage();

        int getPlayerDamagePerSecond();

        int getMoneyAmount();

        int getMoneyPerSecond();

        void saveState(Context context);

        void update();

        int getMonstersKilled();
    }

}
