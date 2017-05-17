package com.example.sauronsarmy.oopp.map;

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
        int  getPlayerMoney();
        int damageMonster(int damage);
        Area getCurrentArea();
        int getGoal();
        int getPathGoal();
        Monster getCurrentMonster();
        boolean tryChangeAreaLevel(int level, int area);
        void nextLevel();
        void previousLevel();

        // methods to be called from Presenter
    }

    interface ModelOps {
        Area  getArea(int index);
        void  setArea(Area area, int index);
        int   getBackgroundRef();
        void  setBackgroundRef(int bgRef);
        void  onDestroy();
    }
}
