package com.example.sauronsarmy.oopp;
import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 24/04/2017.
 */

class MainPresenter implements MainMVPInterface.PresenterOps {
    // View reference
    WeakReference<MainMVPInterface.ViewOps> mView;

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
