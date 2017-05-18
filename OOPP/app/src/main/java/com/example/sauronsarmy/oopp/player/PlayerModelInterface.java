package com.example.sauronsarmy.oopp.player;

import android.content.Context;

/**
 * Created by Erik on 13/04/17.
 */

public interface PlayerModelInterface {

    void setLastLogOn(long newValue);
    long getLastLogOn();

    void setDamagePerSecond(int newValue);
    int getDamagePerSecond();

    void setDamage(int newValue);
    int getDamage();

    void removeMoney(int money);
    void addMoney(int money);
    int getMoney();

    void loadState(Context context);
    void saveState(Context context);

    void setMoneyPerSecond(int newValue);
    int getMoneyPerSecond();
}
