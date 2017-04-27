package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 24/04/2017.
 */

/* "Umbrella" class containing the interfaces
  * used to communicate between MVP layers.
  */
interface MapMVPInterface {

    interface ViewOps {
        // methods to be called from View
    }

    interface PresenterOps{
        int    getBackgroundRef();
        Area[] getAreas();
        Area   getArea();
        void onError(String errorMsg);
        // methods to be called from Presenter
    }

    interface ModelOps {
        Area  getArea(int index);
        void  setArea(Area area, int index);
        int   getBackgroundRef();
        void  setBackgroundRef(int bgRef);
        void  onDestroy();
        // methods to be called from Model
    }
}
