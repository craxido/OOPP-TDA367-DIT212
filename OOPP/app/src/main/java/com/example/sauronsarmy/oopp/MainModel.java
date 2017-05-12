package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sjöström Erik
 * Handles the save game logic.
 */
class MainModel implements MainMVPInterface.ModelInterface {

    private SharedPreferences saveState;
    private SharedPreferences.Editor editor;
    private static String TAG = "MainModel";
    /**
     * Indicates whether there is a previous state to load.
     */
    private boolean hasSaveToLoad;

    MainModel() {
        hasSaveToLoad = true;
    }

    @Override
    public boolean hasSaveToLoad() {
        return hasSaveToLoad;
    }

    /**
     * Saves the current state of the player to a SharedPreference file, which
     * is just a glorified Key-Value storage.
     * When apply() is called this information is asynchronously written to the
     * disk so it doesn't block the main execution thread.
     * @param context Context from which this method was called.
     * @param currentState the state to be saved.
     */
    @Override
    public void saveState(Context context, Map currentState,
                                           Map currentShopUpgrade,
                                           Map currentHomeUpgrade) {

        Log.i(TAG, "Saving player state");
        hasSaveToLoad = true;

        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
        editor = saveState.edit();
        // Player state
        editor.putInt("damage",        (int) currentState.get("damage"));
        editor.putInt("dps",           (int) currentState.get("dps"));
        editor.putInt("money",         (int) currentState.get("money"));
        editor.putInt("moneyPerSec",  (int) currentState.get("moneyPerSec"));
        editor.putLong("lastLogOn",    (long) currentState.get("lastLogOn"));
        // Shop state
        editor.putInt("damageUpgrade",    (int) currentShopUpgrade.get("damageUpgrade"));
        editor.putInt("dpsUpgrade",   (int) currentShopUpgrade.get("dpsUpgrade"));
        // Home state
        editor.putInt("oil", (int) currentHomeUpgrade.get("oil"));
        editor.apply();
    }

    /**
     * Loads the previous state and packages it up as a HashMap.
     * If there is no previous state, returns default values.
     * @param context The context from which this method was called.
     * @return A HashMap containing the previous state.
     */
    @Override
    public Map loadState(Context context) {
        Log.i(TAG, "Loading last player state");
        hasSaveToLoad = false;

       saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
       return new HashMap<String, Object>() {
           {
               put("damage",      saveState.getInt("damage", 10));
               put("dps",         saveState.getInt("dps", 0));
               put("money",       saveState.getInt("money", 0));
               put("moneyPerSec", saveState.getInt("moneyPerSec", 0));
               put("lastLogOn",   saveState.getLong("lastLogOn", -1));
           }
       };
   }


    @Override
    public Map<String, Integer> loadShopUpgrade(Context context) {
        Log.i(TAG, "Loading last shop state");
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
        return new HashMap<String, Integer>() {
            {
                put("damageUpgrade",  saveState.getInt("damageUpgrade", 0));
                put("dpsUpgrade", saveState.getInt("dpsUpgrade", 0));
            }
        };
    }

    @Override
    public Map<String, Integer> loadHomeUpgrade(Context context) {
        Log.i(TAG, "Loading last home state");
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
        return new HashMap<String, Integer>() {
            {
                put("oil",  saveState.getInt("oil", 1));
            }
        };

    }

    /**
     * SharedPreference is (for some reason) not able to save doubles.
     * This leaves me with two options, either casting the double to a float,
     * which is a lossy conversion and widely regarded as a bad move. Or
     * I could convert the value of the double to it's raw long bits equivalent
     * and store this as a long instead. I choose the latter.
     * @param heathen The value to convert
     * @return The converted value
     */
    private long doubleToLong(double heathen) {
        return Double.doubleToRawLongBits(heathen);
    }

    /**
     * Reverts the converted double value to its true form.
     * @param convert The converted value.
     * @return The true form
     */
    private double longToDouble(long convert) {
        return Double.longBitsToDouble(convert);
    }

}
