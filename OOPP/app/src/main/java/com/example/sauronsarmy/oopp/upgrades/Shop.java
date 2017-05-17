package com.example.sauronsarmy.oopp.upgrades;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

/**
 * Created by bunnyfiscuit on 05/04/17.
 *
 */

class Shop implements ShopMVPInterface.Model {

    private static final Shop shopInstance = new Shop();
    private static final String TAG = "Shop";
    private SharedPreferences saveState;
    private SharedPreferences.Editor editor;

    // Variables
    private PlayerModelInterface player = PlayerModel.getInstance();

    private Upgrade damageUpgrade = new Upgrade(2,50);
    private Upgrade dpsUpgrade = new Upgrade(1,100);
    private int damageUpgradeCounter = 1;
    private int dpsUpgradeCounter = 1;

    public static Shop getInstance() { return shopInstance; }

    /** Buy damage upgrade for player.
     *  adds to player damage, and applies the cost
     *  updates how many upgrades has been done, and updates the upgrade
     */
    public boolean buyDamageUpgrade(){
        if(player.getMoney() >= damageUpgrade.getCost()) {
            player.removeMoney(damageUpgrade.getCost());
            player.setDamage(player.getDamage() + damageUpgrade.getStat());

            damageUpgradeCounter++;
            damageUpgrade.updateStat(damageUpgradeCounter);
            damageUpgrade.updateCost(damageUpgradeCounter);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Similar to above, except this upgrade is for damage per second.
     */
    public boolean buyDPSUpgrade(){
        if (player.getMoney() >= dpsUpgrade.getCost()) {
            player.removeMoney(dpsUpgrade.getCost());
            player.setDamagePerSecond(player.getDamagePerSecond() + dpsUpgrade.getStat());

            dpsUpgradeCounter++;
            dpsUpgrade.updateStat(dpsUpgradeCounter);
            dpsUpgrade.updateCost(dpsUpgradeCounter);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Setters & Getters
     */

    public Upgrade getDamageUpgrade(){
        return damageUpgrade;
    }

    public int getDamageUpgradeCounter(){
        return damageUpgradeCounter;
    }

    public Upgrade getDPSUpgrade(){
        return dpsUpgrade;
    }

    public int getDPSUpgradeCounter(){
        return dpsUpgradeCounter;
    }

    public int getPlayerMoney(){
        return player.getMoney();
    }

    @Override
    public void saveState(Context context) {
        Log.i(TAG, "Saving shop state");
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                                                Context.MODE_PRIVATE);
        editor = saveState.edit();
        editor.putInt("damageUpgrade", getDamageUpgradeCounter());
        editor.putInt("dpsUpgrade",    getDPSUpgradeCounter());
        editor.apply();
    }

    @Override
    public void loadState(Context context) {
        Log.i(TAG, "Loading shop state");
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                Context.MODE_PRIVATE);
        damageUpgradeCounter = saveState.getInt("damageUpgrade", 0);
        dpsUpgradeCounter    = saveState.getInt("dpsUpgrade", 0);
    }
}
