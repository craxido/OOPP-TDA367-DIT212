package com.example.sauronsarmy.oopp.player;

import android.content.Context;

/**
 * Created by Erik on 13/04/17.
 * @author erik
 */

public interface PlayerModelInterface {

    /**
     * Updates the state of the variable damagePerSecond
     * @param newValue The new value
     */
    void setDamagePerSecond(int newValue);

    /**
     * @return The state of the variable damagePerSecond
     */
    int getDamagePerSecond();

    /**
     * Updates the state of the variable damage
     * @param newValue The new value
     */
    void setDamage(int newValue);

    /**
     * @return The state of the variable damage
     */
    int getDamage();

    /**
     * Removes an amount from the variable money
     * @param money Amount to remove
     */
    void removeMoney(int money);

    /**
     * Adds an amount to the variable money
     * @param money Amount to add
     */
    void addMoney(int money);

    /**
     * @return The state of the variable money
     */
    int getMoney();

    /**
     * Sets the state of the variable moneyPerSecond
     * @param moneyPerSecond The new value
     */
    void setMoneyPerSecond(int moneyPerSecond);

    /**
     * @return The state of the variable moneyPerSecond
     */
    int getMoneyPerSecond();

    /**
     * Saves the state of all the variables in the model to
     * the internal storage.
     * @param context Used to access the internal storage
     */
    void saveState(Context context);

    /**
     * Updates the state of the variables in the model with
     * the state which is saved in the internal storage.
     * @param context Used to access the internal storage
     */
    void loadState(Context context);
}
