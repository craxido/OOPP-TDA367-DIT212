package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.*;
import java.util.Map;

/**
 * Created by erik on 25/04/17.
 */

public class MainModel implements MainMVPInterface.ModelInterface {

    private SharedPreferences saveState;
    private SharedPreferences.Editor editor;
    private Map<String, Object> stateMap;

    private static final MainModel ourInstance = new MainModel();

    public static MainModel getInstance() {
        return ourInstance;
    }

    private MainModel() {
    }

    /**
     * Saves the current state of the player to a SharedPreference file, which
     * is just a glorified Key-Value storage.
     * When apply() is called this information is asynchronously written to the
     * disk so it doesn't block the main execution thread.
     * @param context Context from which this method was called.
     * @param currentState the state to be saved.
     */
    void saveState(Context context, Map<String, Object> currentState) {
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
        editor = saveState.edit();
        editor.putInt("damage",       (int) currentState.get("damage"));
        editor.putFloat("damageMult", (float) currentState.get("damageMult"));
        editor.putInt("money",        (int) currentState.get("money"));
        editor.putInt("moneyPerSec",  (int) currentState.get("moneyPerSec"));
        editor.putLong("lastLogOn",   (long) currentState.get("lastLogOn"));
        editor.apply();
    }

   Map<String, Object> loadState(Context context) {
       saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
       stateMap = new HashMap<String, Object>() {
           {
               put("damage",     saveState.getInt("damage", 10));
               put("damageMult", saveState.getFloat("damageMult", 1.0f));
               put("money",      saveState.getInt("money", 10));
               put("moneyPerSec", saveState.getInt("moneyPerSec", 0));
               put("lastLogOn", saveState.getLong("lastLogOn", -1));
           }
       };
       return  stateMap;
   }
}
