package com.example.sauronsarmy.oopp;

import android.content.Context;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.monsterPack.IMonster;

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
        IMonster getCurrentMonster();
        void saveState(Context value1);
        int getGoal();
        int getPathGoal();
        int getBGRef();
        boolean nextLevel();
        boolean previousLevel();
        int getPlayerMoney();
        void update();
        int getNextArrowImage();
        int getPrevArrowImage();
        void checkLevelUnlocked(int pathGoal);
        void incrementCurrentLevel();
        void decrementCurrentLevel();
        int getLevelIndex();
        int getAreaIndex();
        }

    interface ModelInterface {
        void saveState(Context value1, java.util.Map currentState);
        java.util.Map loadState(Context context);
        boolean hasSaveToLoad();
        void checkLevelUnlocked(int pathGoal);
        int getNextArrowImage();
        int getPrevArrowImage();
        void incrementCurrentLevel();
        void decrementCurrentLevel();
    }



}
