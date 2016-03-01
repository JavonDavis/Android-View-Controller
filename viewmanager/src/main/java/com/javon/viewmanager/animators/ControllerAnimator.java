package com.javon.viewmanager.animators;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 21/02/16.
 */
public abstract class ControllerAnimator extends Animation implements Animation.AnimationListener {

    private View mOldView;
    private View mNewView;

    private Context mContext;

    private static final int DEFAULT_DURATION = 1000;

    /**
     * Default Constructor
     */
    public ControllerAnimator(Context context)
    {
        if(context != null)
            setContext(context);
        else
            throw new NullPointerException("Context cannot be null");
        setDuration(DEFAULT_DURATION);
        setAnimationListener(this);
    }

    public ControllerAnimator(Context context, View oldView, View newView)
    {
        this.mOldView = oldView;
        this.mNewView = newView;

        if(this.mNewView != null && this.mOldView != null)
            this.mNewView.setLayoutParams(this.mOldView.getLayoutParams());
        else
            throw new NullPointerException("View parameters cannot be null");

        if(context != null)
            setContext(context);
        else
            throw new NullPointerException("Context cannot be null");

        setDuration(DEFAULT_DURATION);
        setAnimationListener(this);
    }

    public View getOldView() {
        return mOldView;
    }

    public void setOldView(View oldView) {
        this.mOldView = oldView;
        if(mNewView != null)
            this.mNewView.setLayoutParams(mOldView.getLayoutParams());
    }

    public View getNewView() {
        return mNewView;
    }

    public void setNewView(View newView) {
        this.mNewView = newView;
        if(mOldView != null)
            this.mNewView.setLayoutParams(mOldView.getLayoutParams());
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
}
