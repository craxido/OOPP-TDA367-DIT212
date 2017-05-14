package com.example.sauronsarmy.oopp.Map;

import android.util.Log;

import com.example.sauronsarmy.oopp.MonsterPack.Monster;
import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import java.lang.ref.WeakReference;

/**
 * @author Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";
    private static MapPresenter mapPresenterInstance = MapPresenter.getInstance();
    private static Map map = Map.getInstance();
    WeakReference<MapMVPInterface.ViewOps> mapView;
    private PlayerModelInterface playerModel;

    public MapPresenter(MapMVPInterface.ViewOps mapView) {
        this.mapView = new WeakReference<>(mapView);
        map = Map.getInstance();
        mapPresenterInstance = this;
        playerModel = PlayerModel.getInstance();
    }

    public static void setBackgroundRef(int ref) {
        map.setBackgroundRef(ref);
    }

    public int getBackgroundRef() {
        return map.getBackgroundRef();
    }

    public Area getArea(int index) {
        return map.getArea(index);
    }

    public void onError(String errorMsg) {
        Log.i(TAG, "Error: " + errorMsg);
    }

    public static MapPresenter getInstance() {
        return mapPresenterInstance;
    }

    public Area getCurrentArea() {
        return map.getCurrentArea();
    }

    public Level getCurrentLevel() {
        return map.getCurrentLevel();
    }

    public void setNewMonster() {
        map.getCurrentArea().getCurrentLevel().setNewMonster();
    }

    public int damageMonster(int damage) {
        return map.getCurrentArea().getCurrentLevel().damageMonster(damage);
    }

    public Monster getCurrentMonster() {
        return map.getCurrentArea().getCurrentLevel().getCurrentMonster();
    }

    public Level makeLevel(areaType areaType) {
        return map.createLevel(areaType);
    }

    public Area[] getAreas() {
        return map.getAreas();
    }

    public int getLevelGoldMultiplier(int areaIndex, int levelIndex){
        return map.getLevelGoldMultiplier(areaIndex, levelIndex);
    }

    public int getLevelHealthMultiplier(int areaIndex, int levelIndex) {
        return map.getLevelHealthMultiplier(areaIndex, levelIndex);
    }

    public int getLevelAmount(int areaIndex) {
        return map.getLevelAmount(areaIndex);
    }

    public int getAreaAmount() {
        return map.getAreaAmount();
    }

    public Area createArea(int areaIndex) {
        return map.createArea(areaIndex);
    }

    public areaType getAreaType(int areaIndex) {
        return map.getAreaType(areaIndex);
    }

    public int getAreaBgRef(int areaIndex) {
        return map.getAreaBgRef(areaIndex);
    }

}
