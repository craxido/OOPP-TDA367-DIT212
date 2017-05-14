package com.example.sauronsarmy.oopp.Map;

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
        int damageMonster(int damage);
        Area getCrn();
        void trChangeAreaLevel(int level, int area);

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
