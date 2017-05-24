package com.example.sauronsarmy.oopp.player;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

/**
 * Created by Erik on 04/04/17.
 * @author erik
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
        return ourInstance;
    }

    public static PlayerModelInterface getInstance() {
        return ourInstance;
    }

    /**
     * {@inheritDoc}
     * Sharedpreference is just a glorified key-value storage, the
     * advantage being that it can be accessed from anywhere in the
     * app, as long as there is a Context to use.
     *
     * Uses .apply() instead of .commit() which means that the state
     * will be saved asynchronously. We avoid race conditions since
     * the state will only be loaded once, when the model is created.
     */
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

    /**
     * {@inheritDoc}
     * Loads default values if no previous state has been saved.
     */
    @Override
    public void loadState(Context context) {
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                Context.MODE_PRIVATE);
        setMoney(saveState.getInt("money", 0));
        setMoneyPerSecond(saveState.getInt("moneyPerSec", 0));
        setDamage(saveState.getInt("damage", 10));
        setDamagePerSecond(saveState.getInt("damagePerSec", 0));
    }
    /**
     * {@inheritDoc}
     * Every second, update the money with the
     * money earned per second
     */
    @Override
    public void update() {
        addMoney(moneyPerSecond);
    }

    /**
     * Updates the state of the variable money
     *      Only exists for consistency
      * @param money New value
     */
    private void setMoney(int money) {
        this.money = money;
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
}

