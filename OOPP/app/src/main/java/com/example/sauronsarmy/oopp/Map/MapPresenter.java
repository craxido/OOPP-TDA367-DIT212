package com.example.sauronsarmy.oopp.Map;

import android.util.Log;

import com.example.sauronsarmy.oopp.MonsterPack.Monster;
import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.MainPresenter;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import java.lang.ref.WeakReference;

/**
 * Author: Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";
    private static MapPresenter mapPresenterInstance;
    private static Map map = Map.getInstance();
    WeakReference<MapMVPInterface.ViewOps> mapView;
    private levelFactory lvlfac;
    private PlayerModelInterface playerModel;

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

    public void getCurrentArea(){
        map.getCurrentArea();
    }

    public void getCurrentLevel(){
        map.getCurrentArea().getCurrentLevel();
    }

    public void setNewMonster(){
        map.getCurrentArea().getCurrentLevel().setNewMonster();
    }

    public int damageMonster(int damage){
        return map.getCurrentArea().getCurrentLevel().damageMonster(playerModel.getDamage());
    }

    public Monster getCurrentMonster(){
        return map.getCurrentArea().getCurrentLevel().getCurrentMonster();
    }
}
