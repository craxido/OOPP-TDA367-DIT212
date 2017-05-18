package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.R.drawable.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sjöström Erik
 * Handles the save game logic.
 */
class MainModel implements MainMVPInterface.ModelInterface {

    private static MainModel mainModelInstance = new MainModel();
    private SharedPreferences saveState;
    private SharedPreferences.Editor editor;
    private static String TAG = "MainModel";
    /**
     *  Variables to check what level you are at,
     *  how many levels you've unlocked,
     *  and the min/max amount of levels.
     * */
    private static int CURRENT_LEVEL = 1;
    private static int LEVELS_UNLOCKED = 1;
    private static final int MAX_LEVELS = 3;
    private static final int MIN_LEVEL = 1;

    /**
     * Indicates whether there is a previous state to load.
     */
    private boolean hasSaveToLoad;

    private MainModel() {
        hasSaveToLoad = true;
    }

    public static MainMVPInterface.ModelInterface getInstance() {
        return mainModelInstance;
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
     *
     * @param context      Context from which this method was called.
     * @param currentState the state to be saved.
     */
    @Override
    public void saveState(Context context, Map currentState) {

        Log.i(TAG, "Saving player state");
        hasSaveToLoad = true;

        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
        editor = saveState.edit();
        // Player state
        editor.putInt("damage", (int) currentState.get("damage"));
        editor.putInt("dps", (int) currentState.get("dps"));
        editor.putInt("money", (int) currentState.get("money"));
        editor.putInt("moneyPerSec", (int) currentState.get("moneyPerSec"));
        editor.putLong("lastLogOn", (long) currentState.get("lastLogOn"));
        editor.apply();
    }

    /**
     * Loads the previous state and packages it up as a HashMap.
     * If there is no previous state, returns default values.
     *
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
                put("damage", saveState.getInt("damage", 10));
                put("dps", saveState.getInt("dps", 0));
                put("money", saveState.getInt("money", 0));
                put("moneyPerSec", saveState.getInt("moneyPerSec", 0));
                put("lastLogOn", saveState.getLong("lastLogOn", -1));
            }
        };
    }

    @Override
    public int getPrevArrowImage(){
        if(CURRENT_LEVEL == MIN_LEVEL) {
            return R.drawable.red_arrow_left;
        } else {
            return R.drawable.green_arrow_left;
        }
    }

    @Override
    public int getNextArrowImage(){
        if(CURRENT_LEVEL < LEVELS_UNLOCKED ){
            return R.drawable.green_arrow_right;
        } else {
            return R.drawable.red_arrow_right;
        }
    }

    @Override
    public void checkLevelUnlocked(int pathGoal){
        if(
                CURRENT_LEVEL == LEVELS_UNLOCKED &&
                        LEVELS_UNLOCKED < MAX_LEVELS &&
                        pathGoal == 10){
            incrementLevelsUnlocked();
        }
    }

    public void incrementCurrentLevel(){
        CURRENT_LEVEL++;
    }

    public void decrementCurrentLevel(){
        CURRENT_LEVEL--;
    }

    public void incrementLevelsUnlocked(){
        LEVELS_UNLOCKED++;
    }
}
