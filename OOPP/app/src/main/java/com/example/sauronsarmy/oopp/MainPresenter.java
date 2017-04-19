package com.example.sauronsarmy.oopp;

import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 2017-04-17.
 */

class MainPresenter implements MainMVPInterface.PresenterOps {

    // Main Model and View references
    WeakReference<MainMVPInterface.ViewOps> mView;
    private MainMVPInterface.ModelOps mModel;
    // Configuration state
    private boolean mIsChanging;

    public MainPresenter(MainMVPInterface.ViewOps mView) {
        this.mView = new WeakReference<>(mView);
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
}
