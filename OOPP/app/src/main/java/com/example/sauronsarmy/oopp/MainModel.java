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

    private static final MainModel ourInstance = new MainModel();

    public static MainModel getInstance() {
        return ourInstance;
    }

    private MainModel() {
    }

    saveState(Context context, Map<String, Object> currentState) {
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
        editor = saveState.edit();
        editor.putInt("damage", (Int) currentState.get("damage"));
        editor.putFloat()
    }
}
