package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Area {

    static levelFactory lvlfac = new levelFactory();

    //Area 1 (Mountain)
    private static final Area area1Instance = new Area(R.drawable.mountainArea,
            com.example.sauronsarmy.oopp.areaType.MOUNTAIN, lvlfac.getLevels(com.example.sauronsarmy.oopp.areaType.MOUNTAIN));
    //Area 2 (Forest)
    private static final Area area2Instance = new Area(R.drawable.forestArea,
            com.example.sauronsarmy.oopp.areaType.FOREST, lvlfac.getLevels(com.example.sauronsarmy.oopp.areaType.FOREST));
    //Area 3 (Volcano)
    private static final Area area3Instance = new Area(R.drawable.volcanoArea,
            com.example.sauronsarmy.oopp.areaType.VOLCANO, lvlfac.getLevels(com.example.sauronsarmy.oopp.areaType.VOLCANO));

    private int imgRef;
    private areaType areaType;
    private Level[] levels;

    public Area(int imgRef, areaType area, Level[] levels){
        this.imgRef = imgRef;
        this.areaType=area;
        this.levels=levels;
    }

    public areaType getArea(){
        return this.areaType;
    }

    public int getImgRef() {
        return imgRef;
    }

    public void setImgRef(int imgRef) {
        this.imgRef = imgRef;
    }

    public Level[] getLevels() {
        return levels;
    }

}
