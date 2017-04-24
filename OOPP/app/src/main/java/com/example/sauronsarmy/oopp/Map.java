package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Map {
    private static final Map mapInstance = new Map();

    private Image img;
    private Area[] areas;
    private Area currentArea;

    static Map getInstance() {
        return mapInstance;
    }

    public Map() {
        this.img = img;
        Level level1 = new Level(30,10,areaType.FOREST);
        level1.setCurrentMonster(new Monster(30,10,R.drawable.bluemonster));

        Area area1 = new Area(null,areaType.FOREST,new Level[]{level1});
        area1.setCurrencLevel(level1);

        areas = new Area[]{area1};
        setCurrentArea(getAreas()[0]);
    }

    public Area[] getAreas() {
        return areas;
    }

    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    public Area getCurrentArea() {
        return currentArea;
    }
}
