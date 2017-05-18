package com.example.sauronsarmy.oopp.map;

import android.content.Context;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.monsterPack.Monster;

/**
 * Author: Jonatan Källman
 */

/* "Umbrella" class containing the interfaces
  * used to communicate between MVP layers.
  */
public interface MapMVPInterface {

    interface ViewOps {
        // methods to be called from View
    }

    interface PresenterOps{
        int    getBackgroundRef();
        Area   getArea(int index);
        void changeArea(int index);
        void onError(String errorMsg);
        void saveState(Context context);
        int  getPlayerMoney();
        void damageMonster();
        void applyDPS();
        Area getCurrentArea();
        int getGoal();
        int getPathGoal();
        Monster getCurrentMonster();
        boolean tryChangeAreaLevel(int level, int area);
        boolean nextLevel();
        boolean previousLevel();

        // methods to be called from Presenter
    }

    interface ModelOps {
        Area  getArea(int index);
        int damageMonster(int damage);
        void  setArea(Area area, int index);
        int   getBackgroundRef();
        void  setBackgroundRef(int bgRef);
        void  onDestroy();
        Area getCurrentArea();
        void setCurrentArea(Area area);
        Area[] getAreas();
        Area createArea(int areaIndex);
        areaType getAreaType(int areaIndex);
        int getAreaBgRef(int areaIndex);
        Level getCurrentLevel();
        Level createLevel(areaType areaType);
        int getLevelGoldMultiplier(int areaIndex, int levelIndex);
        int getLevelHealthMultiplier(int areaIndex, int levelIndex);
        int getLevelAmount(int areaIndex);
        int getAreaAmount();
        // methods to be called from Model
    }
}
