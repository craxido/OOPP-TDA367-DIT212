package com.example.sauronsarmy.oopp.Map;

import android.util.Log;

import com.example.sauronsarmy.oopp.MainPresenter;

import java.lang.ref.WeakReference;

/**
 * Author: Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";
    private MapPresenter mapPresenterInstance;
    private static MapMVPInterface.ModelOps map;
    private MainPresenter mainPresenter;

    public MapPresenter() {
        mainPresenter = MainPresenter.getInstance();
        mapPresenterInstance = this;
        map = Map.getInstance();
    }

    public static void setBackgroundRef(int ref){
        map.setBackgroundRef(ref);
    }

    public int getBackgroundRef(){
        return map.getBackgroundRef();
    }

    public Area getArea(int index){
        return map.getArea(index);
    }

    public void onError(String errorMsg){
        Log.i(TAG, "Error: " + errorMsg);
    }

    public int getPlayerMoney(){
        return map.getPlayerMoney();
    }

}
