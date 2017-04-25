package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.*;
import java.util.Map;

/**
 * @author Sjöström Erik
 */

public class MainModel implements MainMVPInterface.ModelInterface {

    private SharedPreferences saveState;
    private SharedPreferences.Editor editor;
    private static boolean hasSaveToLoad = true;

    MainModel() {
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
    public void saveState(Context context, Map currentState) {

        hasSaveToLoad = true;

        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
        editor = saveState.edit();
        editor.putInt("damage",       (int) currentState.get("damage"));
        editor.putFloat("damageMult", (float) currentState.get("damageMult"));
        editor.putInt("money",        (int) currentState.get("money"));
        editor.putInt("moneyPerSec",  (int) currentState.get("moneyPerSec"));
        editor.putLong("lastLogOn",   (long) currentState.get("lastLogOn"));
        editor.apply();
    }

    //TODO Docstring it!
    @Override
    public Map loadState(Context context) {

        hasSaveToLoad = false;

       saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
       return new HashMap<String, Object>() {
           {
               put("damage",      saveState.getInt("damage", 10));
               put("damageMult",  saveState.getFloat("damageMult", 1.0f));
               put("money",       saveState.getInt("money", 10));
               put("moneyPerSec", saveState.getInt("moneyPerSec", 0));
               put("lastLogOn",   saveState.getLong("lastLogOn", -1));
           }
       };
   }
}
