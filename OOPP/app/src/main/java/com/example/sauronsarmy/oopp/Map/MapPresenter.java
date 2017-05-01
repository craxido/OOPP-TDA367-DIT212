package com.example.sauronsarmy.oopp.Map;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 2017-05-01.
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";
    private static MapPresenter mapPresenterInstance;
    Map map = Map.getInstance();
    WeakReference<MapMVPInterface.ViewOps> mapView;

    public MapPresenter(MapMVPInterface.ViewOps mapView) {
        this.mapView = new WeakReference<>(mapView);
        map = Map.getInstance();
        mapPresenterInstance =this;
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
