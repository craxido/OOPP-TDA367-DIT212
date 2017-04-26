package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Map implements MapMVPInterface.ModelOps {


    private static Area[] areas;
    private Area currentArea;
    private static final Map mapInstance = new Map(R.drawable.mapbg, createAreas());
    private int bgRef;


    static Map getInstance() {
        return mapInstance;
    }


    private Map(int bgRef, Area[] areas) {
        this.bgRef = bgRef;
        this.areas = areas;
        this.currentArea = areas[0];
    }

    //Creates areas for the mapInstance
    private static Area[] createAreas(){
        levelFactory lvlfac = new levelFactory();
        Area[] areas= new Area[3];

        //Area 1 (Mountain)
        areas[0]=new Area(R.drawable.mountainarea, areaType.MOUNTAIN, lvlfac.getLevels(areaType.MOUNTAIN));
        //Area 2 (Forest)
        areas[1]=new Area(R.drawable.forestarea, areaType.FOREST, lvlfac.getLevels(areaType.FOREST));
        //Area 3 (Volcano)
        areas[2]=new Area(R.drawable.volcanoarea, areaType.VOLCANO, lvlfac.getLevels(areaType.VOLCANO));
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
        return this.areas[index];
    }

    @Override
    public void setArea(Area area, int index) {
        this.areas[index] = area;
    }

    @Override
    public void onDestroy() {}

    public Area getCurrentArea(){

        return currentArea;
    }

}
