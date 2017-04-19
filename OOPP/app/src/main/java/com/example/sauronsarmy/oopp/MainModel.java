package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-19.
 */

class MainModel implements MainMVPInterface.ModelOps {

    /*
    * MainModel is a singleton and is only one instance.
    */
    private static final MainModel mainInstance = new MainModel();

    public static MainModel getInstance() {
        return mainInstance;
    }

    /*
    * Constructor is private, implemented as a singleton.
    */
    private MainModel(){
        //Unsure what values are needed if any.
    }

    @Override
    public void onDestroy() {} //To be implemented

}
