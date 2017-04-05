package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Map {
    private Image img;
    private Area[] areas;

    public Map(Image img, Area[] areas) {
        this.img = img;
        this.areas = areas;
    }

}
