package com.example.sauronsarmy.oopp;

import android.content.Context;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.monsterPack.Monster;

/**
   * Created by Jonatan on 2017-04-17.
   */
/* "Umbrella" class containing the interfaces
  * used to communicate between MVP layers.
  */
public interface MainMVPInterface {

    interface ViewOps {
         // methods to be called from View
             }

    interface PresenterOps{
        boolean getLvlCmp();
        boolean getAreaCmp();
        void monsterClicked();
        Monster getCurrentMonster();
        void saveState(Context value1);
        int getGoal();
        int getPathGoal();
        int getBGRef();
        void nextLevel();
        void previousLevel();
        int getPlayerMoney();
        void update();
        }

    interface ModelInterface {
        void saveState(Context value1, java.util.Map currentState);
        java.util.Map loadState(Context context);
        boolean hasSaveToLoad();
    }



}
