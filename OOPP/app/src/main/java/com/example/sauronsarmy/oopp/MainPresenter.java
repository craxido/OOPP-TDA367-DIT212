package com.example.sauronsarmy.oopp;

import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 2017-04-17.
 */

public class MainPresenter implements IMainMVP.RequiredPresenterOps, IMainMVP.PresenterOps {

    // Main Model and View references
    WeakReference<IMainMVP.RequiredViewOps> mView;
    private IMainMVP.ModelOps mModel;
    // Configuration state
    private boolean mIsChanging;

    public MainPresenter(IMainMVP.RequiredViewOps mView) {
        this.mView = new WeakReference<>(mView);
    }

    // A configuration changed
    public void onConfigChange(IMainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }
}
