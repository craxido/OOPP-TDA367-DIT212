package com.example.sauronsarmy.oopp;

import android.content.Context;

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
        public Monster getCurrentMonster();
         // methods to be called from Presenter
        void saveState(Context value1);
        void loadState(Context value1);
        }

    interface ModelInterface {
        void saveState(Context value1, java.util.Map value2);
        java.util.Map loadState(Context value1);
        boolean hasSaveToLoad();
    }


}
