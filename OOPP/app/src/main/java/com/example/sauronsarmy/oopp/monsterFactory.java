package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Filip on 2017-04-05.
 */

class monsterFactory {
    public monsterFactory(){}

    public Monster getMonster(double hp, int gold, areaType type){
        //-TODO
        Image img = null;

        switch (type){

            case FOREST: return new Monster(hp, gold,null,null );
            case MOUNTAIN:return new Monster(hp, gold,null,null );
            case VOLCANO:return new Monster(hp, gold,null,null );
            default: return null;

        }




    }
}
