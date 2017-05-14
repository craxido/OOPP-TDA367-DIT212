package com.example.sauronsarmy.oopp;

import android.content.Context;

import com.example.sauronsarmy.oopp.monsterPack.Monster;

/**
   * Created by Jonatan on 2017-04-17.
   */
/* "Umbrella" class containing the interfaces
  * used to communicate between MVP layers.
  */
interface MainMVPInterface {

    interface ViewOps {
         // methods to be called from View
             }

    interface PresenterOps{
         void onConfigChange(ViewOps view);
         void onDestroy(boolean isChangingConfig);
         void onError(String errorMsg);

        void monsterClicked();
        Monster getCurrentMonster();
         // methods to be called from Presenter
        void saveState(Context value1);
        void loadState(Context value1);
        }

    interface ModelInterface {
        void saveState(Context value1, java.util.Map currentState,
                                       java.util.Map currentShopUpgrade,
                                       java.util.Map currentHomeUpgrade);
        java.util.Map loadState(Context context);
        java.util.Map loadShopUpgrade(Context context);
        java.util.Map loadHomeUpgrade(Context context);
        boolean hasSaveToLoad();
    }


}
