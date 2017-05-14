package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.R;

/**
 * Author: Jonatan Källman
 */

public class Map implements MapMVPInterface.ModelOps {


    private static Area[] areas;
    private Area currentArea;
    private static final Map mapInstance = new Map();
    private static levelFactory lvlfac;
    private int bgRef;


    public static Map getInstance() {
        return mapInstance;
    }


    private Map() {
        bgRef = R.drawable.mapbg;
        areas = createAreas();
	    currentArea = areas[0];

    }

    //Creates areas for the mapInstance
    private static Area[] createAreas(){
        Area[] areas= new Area[3];
        lvlfac = new levelFactory();
        //Area 1 (Mountain)
        areas[0] = new Area(R.drawable.mountainarea, areaType.MOUNTAIN, lvlfac.getLevels(areaType.MOUNTAIN));
        //Area 2 (Forest)
        areas[1] = new Area(R.drawable.forestarea, areaType.FOREST, lvlfac.getLevels(areaType.FOREST));
        //Area 3 (Volcano)
        areas[2] = new Area(R.drawable.volcanoarea, areaType.VOLCANO, lvlfac.getLevels(areaType.VOLCANO));

        return areas;
    }

    @Override
    public int getBackgroundRef() {
        return this.bgRef;
    }

    @Override
    public void setBackgroundRef(int bgRef) {
        this.bgRef = bgRef;
    }

    @Override
    public Area getArea(int index) {
        return areas[index];
    }

    @Override
    public void setArea(Area area, int index) {
        areas[index] = area;
    }

    @Override
    public void onDestroy() {}

    public Area getCurrentArea(){
        return currentArea;
    }

    public void setCurrentArea(Area area){
        currentArea=area;
    }

    public Area[] getAreas(){
        return areas;
    }

}