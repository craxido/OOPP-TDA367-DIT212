package com.example.sauronsarmy.oopp;

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
         // methods to be called from Presenter
        }

    interface ModelOps {
         void onDestroy();
       // methods to be called from Model
    }
}
