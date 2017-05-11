package com.example.sauronsarmy.oopp.Map;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Author: Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";
    private static final MapPresenter mapPresenterInstance = new MapPresenter();
    private static Map map = Map.getInstance();
    WeakReference<MapMVPInterface.ViewOps> mapView;

    private MapPresenter() {
        //this.mapView = new WeakReference<>(mapView);
        //map = Map.getInstance();

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

    public Area getCurrentArea(){ return map.getCurrentArea();}

    public int getGoal(){return getCurrentArea().getCurrentLevel().getGoal();}
    public int getPathGoal(){return getCurrentArea().getCurrentLevel().getPathToGoal();}

    public void onError(String errorMsg){
        Log.i(TAG, "Error: " + errorMsg);
    }

    public static MapPresenter getInstance(){

        return mapPresenterInstance;
    }

    public void changeArea(int index){
        map.setCurrentArea(map.getArea(index));
        int imgref= map.getArea(index).getImgRef();
        setBackgroundRef(imgref);
        map.setBackgroundRef(imgref);

    }

}
