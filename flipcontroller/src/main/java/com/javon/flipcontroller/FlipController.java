package com.javon.flipcontroller;

import android.view.View;
import android.view.animation.Animation;

import java.util.ArrayList;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 21/02/16.
 */
public class FlipController {

    private Animation mDefaultForwardAnimation;
    private Animation mDefaultBackwardAnimation;

    private ArrayList<View> mViews;

    /**
     *
     * @param views - list of views to be used in the correct order
     */
    public FlipController(ArrayList<View> views, boolean useDefaultListeners)
    {
        this.mViews = views;
        if(useDefaultListeners)
            setupListeners();
    }

    /**
     *
     * @param views - list of views to be used in the correct order
     * @param defaultForwardAnimation - animation to be used whenever going to the next view in the list
     * @param defaultBackwardAnimation - animation to be used whenever going back to the previous view in the list
     */
    public FlipController(ArrayList<View> views,Animation defaultForwardAnimation, Animation defaultBackwardAnimation, boolean useDefaultListeners) {
        this(views,useDefaultListeners);
        this.mDefaultForwardAnimation = defaultForwardAnimation;
        this.mDefaultBackwardAnimation = defaultBackwardAnimation;
    }

    /**
     * Go to the next view
     */
    public void next()
    {

    }

    /**
     * Go back to the previous view
     */
    public void previous()
    {

    }

    /**
     * set default onClicklistener on all the views
     */
    private void setupListeners() {
        for(View view:mViews)
        {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    next();
                }
            });
        }
    }

    /**
     *
     * @return the default animation for next
     */
    public Animation getDefaultForwardAnimation() {
        return mDefaultForwardAnimation;
    }

    /**
     *
     * @param defaultForwardAnimation the default animation for next
     */
    public void setDefaultForwardAnimation(Animation defaultForwardAnimation) {
        this.mDefaultForwardAnimation = defaultForwardAnimation;
    }

    /**
     *
     * @return the default animation for previous
     */
    public Animation getDefaultBackwardAnimation() {
        return mDefaultBackwardAnimation;
    }

    /**
     *
     * @param defaultBackwardAnimation the default animation for previous
     */
    public void setDefaultBackwardAnimation(Animation defaultBackwardAnimation) {
        this.mDefaultBackwardAnimation = defaultBackwardAnimation;
    }

    public ArrayList<View> getViews() {
        return mViews;
    }

    public void setViews(ArrayList<View> views) {
        this.mViews = views;
    }
}
