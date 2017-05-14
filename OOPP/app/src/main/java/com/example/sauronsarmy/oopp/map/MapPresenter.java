package com.example.sauronsarmy.oopp.map;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Author: Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";
    private static MapPresenter mapPresenterInstance;
    private static Map map = Map.getInstance();
    WeakReference<MapMVPInterface.ViewOps> mapView;

    public MapPresenter(MapMVPInterface.ViewOps mapView) {
        this.mapView = new WeakReference<>(mapView);
        map = Map.getInstance();
        mapPresenterInstance =this;
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

    public static MapPresenter getInstance(){

        return mapPresenterInstance;
    }

}
