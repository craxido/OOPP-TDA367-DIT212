package com.example.sauronsarmy.oopp.Map;

import android.util.Log;

import com.example.sauronsarmy.oopp.MainPresenter;

import java.lang.ref.WeakReference;

/**
 * Author: Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";
    private static final MapPresenter mapPresenterInstance = new MapPresenter();
    private static Map map;
    WeakReference<MapMVPInterface.ViewOps> mapView;
    private static int bgref;

    private MapPresenter() {
        //this.mapView = new WeakReference<>(mapView);
        map = Map.getInstance();
        setBackgroundRef(map.getCurrentArea().getImgRef());
    }

    public static void setBackgroundRef(int ref){
        bgref = ref;
    }

    public int getBackgroundRef(){
        return bgref;
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
    }

    public int damageMonster(int damage){
        int ret = map.getCurrentArea().getCurrentLevel().damageMonster(damage);
        if (ret >0){
            map.getCurrentArea().checkComplete();
        }
        return ret;

    }

    public Area getCrn(){
        return map.getCurrentArea();
    }

    @Override
    public void trChangeAreaLevel(int level, int area) {

    }

    public void changeLvl(int index){

        getCurrentArea().setCurrentLevel(getCurrentArea().getLevels()[index]);

    }

}
