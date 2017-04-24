package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Map implements MapMVPInterface.ModelOps {
    private static final Map mapInstance = new Map(R.drawable.mapbg, Area[] areas);
    private int bgRef;
    private Area[] areas;

    static Map getInstance() {
        return mapInstance;
    }

    private Map(int bgRef, Area[] areas) {
        this.bgRef = bgRef;
        this.areas = areas;
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
    public void onDestroy() {
    }
}
