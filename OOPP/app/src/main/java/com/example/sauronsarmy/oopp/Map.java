package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Map {
    private static final Map mapInstance = new Map();

    private Image img;
    private Area[] areas;

    static Map getInstance() {
        return mapInstance;
    }

    public Map() {
        this.img = img;
        this.areas = areas;
    }

}
