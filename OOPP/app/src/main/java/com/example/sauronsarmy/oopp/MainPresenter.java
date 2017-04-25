package com.example.sauronsarmy.oopp;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.Map;

/**
 * Created by Jonatan on 24/04/2017.
 */

class MainPresenter implements MainMVPInterface.PresenterOps {
    // View reference
    WeakReference<MainMVPInterface.ViewOps> mView;
    PlayerModelInterface playerModel;
    MainMVPInterface.ModelInterface mainModel;

    public MainPresenter(MainMVPInterface.ViewOps mView) {
                this.mView = new WeakReference<>(mView);
        playerModel = PlayerModel.getInstance();
        mainModel = new MainModel();
    }

    // A configuration changed
    @Override
    public void onConfigChange(MainMVPInterface.ViewOps view) {
                mView = new WeakReference<>(view);
    }

    @Override
    public void onDestroy(boolean isChangingConfig){} //To be implemented
    @Override
    public void onError(String msg){} //To be implemented

    @Override
    //TODO When Map has its own package, simplify
    public void saveState(Context context) {
        java.util.Map currentState = null; // Implement get state in PlayerModel
        mainModel.saveState(context, currentState);
    }

    @Override
    public Map loadState(Context context) {
        return mainModel.loadState(context);
    }
}
