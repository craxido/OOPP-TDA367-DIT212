package com.example.sauronsarmy.oopp.player;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sauronsarmy.oopp.MainActivity;
import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 04/04/17.
 * Implemented the PlayerModel class using the singleton
 * pattern, since there should only be one instance
 * of a player.
 */

public class PlayerModel implements PlayerModelInterface, ClockListener {

    private static PlayerModel ourInstance;
    private static SharedPreferences saveState;

    private int damage;
    private int damagePerSecond;
    private int money;
    private int moneyPerSecond;

    /*
    Constructor is private since this implemented as a singelton.
    Only the model itself is allowed to create a new instance. Everyone
    else just gets to talk with this instance.
     */
    private PlayerModel(Context context) {
        loadState(context.getApplicationContext());
        Runner.getInstance().register(this);
    }

    public static PlayerModelInterface getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new PlayerModel(context.getApplicationContext());
        return getInstance();
    }

    public static PlayerModelInterface getInstance() {
        return ourInstance;
    }

    @Override
    public int getDamagePerSecond() {
        return damagePerSecond;
    }

    @Override
    public void setDamagePerSecond(int damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void loadState(Context context) {
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                Context.MODE_PRIVATE);
        setMoney(saveState.getInt("money", 0));
        setMoneyPerSecond(saveState.getInt("moneyPerSec", 0));
        setDamage(saveState.getInt("damage", 10));
        setDamagePerSecond(saveState.getInt("damagePerSec", 0));
    }

    @Override
    public void saveState(Context context) {
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                                                 Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveState.edit();
        editor.putInt("money", getMoney());
        editor.putInt("moneyPerSec", getMoneyPerSecond());
        editor.putInt("damage", getDamage());
        editor.putInt("damagePerSec", getDamagePerSecond());
        editor.apply();
    }

    private void setMoney(int money) {
        this.money = money;
    }

    @Override
    public void addMoney(int money) { this.money += money; }

    @Override
    public void removeMoney(int money) { this.money -= money; }

    @Override
    public int getMoneyPerSecond() {
        return moneyPerSecond;
    }

    @Override
    public void setMoneyPerSecond(int moneyPerSecond) {
        this.moneyPerSecond = moneyPerSecond;
    }

    /**
     * Every second, update the money
     */
    @Override
    public void update() {
        addMoney(moneyPerSecond);
    }
}

