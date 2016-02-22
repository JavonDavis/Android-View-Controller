package com.javon.flipcontroller;

import android.view.View;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 21/02/16.
 */
public class FlipController {

    private ControllerAnimator mDefaultForwardAnimation;
    private ControllerAnimator mDefaultBackwardAnimation;

    private ArrayList<View> mViews;
    private ListIterator<View> iterator;

    /**
     *
     * @param views - list of views to be used in the correct order
     */
    public FlipController(ArrayList<View> views, boolean useDefaultListeners)
    {
        this.mViews = views;
        this.iterator = views.listIterator();

        if(useDefaultListeners)
            setupListeners();
    }

    /**
     *
     * @param views - list of views to be used in the correct order
     * @param defaultForwardAnimation - animation to be used whenever going to the next view in the list
     * @param defaultBackwardAnimation - animation to be used whenever going back to the previous view in the list
     */
    public FlipController(ArrayList<View> views,ControllerAnimator defaultForwardAnimation, ControllerAnimator defaultBackwardAnimation, boolean useDefaultListeners) {
        this(views,useDefaultListeners);
        this.mDefaultForwardAnimation = defaultForwardAnimation;
        this.mDefaultBackwardAnimation = defaultBackwardAnimation;
    }

    /**
     * Go to the next view
     */
    public void next()
    {
        if(iterator.hasNext()) {
            View currentView = iterator.next();

            if (iterator.nextIndex() < mViews.size()) {
                View nextView = mViews.get(iterator.nextIndex());

                ControllerAnimator animator = getDefaultForwardAnimation();
                if (animator != null) {
                    currentView.startAnimation(animator);
                } else {
                    animator = new RightFlipAnimation(currentView, nextView);
                    currentView.startAnimation(animator);
                }

            }
        }
    }

    /**
     * Go to the next view using the animator passed in
     * @param animator - the animator to use for the next view change
     */
    public void next(ControllerAnimator animator)
    {
        View currentView = getCurrentView();
        if(animator != null)
        {
            currentView.startAnimation(animator);
            iterator.next();
        }
        else
        {
            throw new NullPointerException("Animator cannot be null");
        }
    }

    /**
     *
     * @return the next view
     */
    public View nextView()
    {
        return mViews.get(iterator.nextIndex());
    }

    /**
     *
     * @return the current view
     */
    public View getCurrentView()
    {
        return mViews.get(iterator.previousIndex()+1);
    }

    /**
     * Go back to the previous view
     */
    public void previous()
    {
        if(iterator.hasPrevious())
        {
            View previousView = iterator.previous();
            View currentView = mViews.get(iterator.nextIndex()-1);

            ControllerAnimator animator = getDefaultBackwardAnimation();
            if(animator != null)
            {
                currentView.startAnimation(animator);
            }
            else
            {
                animator = new LeftFlipAnimation(currentView,previousView);
                currentView.startAnimation(animator);
            }

        }
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
    public ControllerAnimator getDefaultForwardAnimation() {
        return mDefaultForwardAnimation;
    }

    /**
     *
     * @param defaultForwardAnimation the default animation for next
     */
    public void setDefaultForwardAnimation(ControllerAnimator defaultForwardAnimation) {
        this.mDefaultForwardAnimation = defaultForwardAnimation;
    }

    /**
     *
     * @return the default animation for previous
     */
    public ControllerAnimator getDefaultBackwardAnimation() {
        return mDefaultBackwardAnimation;
    }

    /**
     *
     * @param defaultBackwardAnimation the default animation for previous
     */
    public void setDefaultBackwardAnimation(ControllerAnimator defaultBackwardAnimation) {
        this.mDefaultBackwardAnimation = defaultBackwardAnimation;
    }

    public ArrayList<View> getViews() {
        return mViews;
    }

    public void setViews(ArrayList<View> views) {
        this.mViews = views;
    }
}
