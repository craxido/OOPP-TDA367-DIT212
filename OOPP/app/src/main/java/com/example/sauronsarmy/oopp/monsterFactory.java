package com.example.sauronsarmy.oopp;


/**
 * Created by Filip on 2017-04-05.
 */

class monsterFactory {
    public monsterFactory(){}

    public Monster getMonster(double hp, int gold, areaType type){


        switch (type){

            case FOREST:return new Monster(hp, gold,R.drawable.mike );
            case MOUNTAIN:return new Monster(hp, gold,R.drawable.bluemonster);
            case VOLCANO:return new Monster(hp, gold ,R.drawable.bluemonster);
            default: return new Monster(hp, gold,R.drawable.mike );

        }


    }
}
