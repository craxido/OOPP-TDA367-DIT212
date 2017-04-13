package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Filip on 2017-04-05.
 */

class monsterFactory {
    public monsterFactory(){}

    public Monster getMonster(double hp, int gold, areaType type){
        //TODO
        //determine way to present image
        Image img = null;

        switch (type){

            case FOREST: return new Monster(hp, gold,null,null,0 );
            case MOUNTAIN:return new Monster(hp, gold,null,null,0 );
            case VOLCANO:return new Monster(hp, gold,null,null ,0);
            default: return null;

        }




    }
}
