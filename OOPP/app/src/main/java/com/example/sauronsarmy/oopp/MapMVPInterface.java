package com.example.sauronsarmy.oopp;

import android.media.Image;

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
        Image  getBackground();
        Area[] getAreas();
        Area   getArea();
        void onError(String errorMsg);
        // methods to be called from Presenter
    }

    interface ModelOps {
        Area  getArea();
        void  setArea(Area area);
        Image getBackground();
        void  setBackground(Image img);
        void  onDestroy();
        // methods to be called from Model
    }
}
