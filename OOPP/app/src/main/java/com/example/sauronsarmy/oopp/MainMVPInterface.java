package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-17.
 */

/* "Umbrella" class containing the interfaces
 * used to communicate between MVP layers.
 */
public interface MainMVPInterface {

    /* Presenter -> View */
    interface RequiredViewOps {
        // methods to be called from View
    }

    /* View -> Presenter */
    interface PresenterOps{
        // methods to be called from View
    }

    /* Model -> Presenter */
    interface RequiredPresenterOps {
        // methods to be called from Model
    }

    /* Presenter -> Model */
    interface ModelOps {
        // model methods
    }
}
