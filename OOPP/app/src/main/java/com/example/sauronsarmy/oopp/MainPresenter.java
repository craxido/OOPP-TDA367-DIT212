package com.example.sauronsarmy.oopp;

import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 2017-04-17.
 */

public class MainPresenter implements MainMVPInterface.RequiredPresenterOps, MainMVPInterface.PresenterOps {

    // Main Model and View references
    WeakReference<MainMVPInterface.RequiredViewOps> mView;
    private MainMVPInterface.ModelOps mModel;
    // Configuration state
    private boolean mIsChanging;

    public MainPresenter(MainMVPInterface.RequiredViewOps mView) {
        this.mView = new WeakReference<>(mView);
    }

    // A configuration changed
    public void onConfigChange(MainMVPInterface.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }
}
