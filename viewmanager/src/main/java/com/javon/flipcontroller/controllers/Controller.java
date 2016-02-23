package com.javon.flipcontroller.controllers;

import android.view.View;

import com.javon.flipcontroller.animators.ControllerAnimator;
import com.javon.flipcontroller.animators.FadingAnimator;
import com.javon.flipcontroller.animators.LeftFlipAnimator;
import com.javon.flipcontroller.animators.RightFlipAnimator;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 21/02/16.
 */
public class Controller {

    private ControllerAnimator mDefaultForwardAnimation;
    private ControllerAnimator mDefaultBackwardAnimation;

    private ArrayList<View> mViews;
    private ListIterator<View> iterator;
    private boolean mLoop;
    private ViewListener listener;
    private boolean mUseDefaultListener;

    /**
     *
     * @param views - list of views to be used in the correct order
     */
    public Controller(ArrayList<View> views)
    {
        this(views,true,false);
    }

    /**
     *
     * @param views - list of views to be used in the correct order
     * @param useDefaultListeners - if true, the default onClick listener will be used which is an onClick listener on the entire view
     */
    public Controller(ArrayList<View> views, boolean useDefaultListeners,boolean loop)
    {
        this(views,useDefaultListeners,loop,null,null);
    }

    /**
     *
     * @param views - list of views to be used in the correct order
     * @param useDefaultListeners - if true, the default onlick listener will be used which is an onClick listener on the entire view
     * @param loop - if true it will loop around the list of views and start from the beginning
     * @param defaultForwardAnimation - animation to be used whenever going to the next view in the list, if null the default is used
     * @param defaultBackwardAnimation - animation to be used whenever going back to the previous view in the list, if null the default is used
     */
    public Controller(ArrayList<View> views, boolean useDefaultListeners, boolean loop, ControllerAnimator defaultForwardAnimation, ControllerAnimator defaultBackwardAnimation) {
        this.mViews = views;
        this.iterator = views.listIterator();

        listener = new ViewListener();

        this.mUseDefaultListener = useDefaultListeners;

        if(useDefaultListeners)
            views.get(0).setOnClickListener(listener);

        this.mLoop = loop;

        if(defaultForwardAnimation == null)
            this.mDefaultForwardAnimation = new FadingAnimator();
        else
            this.mDefaultForwardAnimation = defaultForwardAnimation;

        if(defaultBackwardAnimation == null)
            this.mDefaultBackwardAnimation = new LeftFlipAnimator();
        else
            this.mDefaultBackwardAnimation = defaultBackwardAnimation;
    }

    /**
     * Go to the next view
     */
    public void next()
    {
        if(iterator.hasNext()) {
            View currentView = iterator.next();
            if(mUseDefaultListener)
                currentView.setOnClickListener(null);

            if (iterator.nextIndex() < mViews.size()) {
                View nextView = mViews.get(iterator.nextIndex());

                if(mUseDefaultListener)
                    nextView.setOnClickListener(listener);

                ControllerAnimator animator = getDefaultForwardAnimation();

                animator.setOldView(currentView);
                animator.setNewView(nextView);

                currentView.startAnimation(animator);

            }
            else
            {
                if(mLoop)
                {
                    iterator = mViews.listIterator();
                    View nextView = mViews.get(0);
                    if(mUseDefaultListener)
                        nextView.setOnClickListener(listener);

                    ControllerAnimator animator = getDefaultForwardAnimation();

                    animator.setOldView(currentView);
                    animator.setNewView(nextView);

                    currentView.startAnimation(animator);
                }
            }
        }
    }

    /**
     * Go to the next view using the animator passed in
     * @param animator - the animator to use for the next view change
     */
    public void next(ControllerAnimator animator){
        if(animator == null)
            throw new NullPointerException("Animator cannot be null");

        if(iterator.hasNext()) {
            View currentView = iterator.next();
            if(mUseDefaultListener)
                currentView.setOnClickListener(null);

            if (iterator.nextIndex() < mViews.size()) {
                View nextView = mViews.get(iterator.nextIndex());
                if(mUseDefaultListener)
                    nextView.setOnClickListener(listener);
                animator.setOldView(currentView);
                animator.setNewView(nextView);

                currentView.startAnimation(animator);

            } else {
                if (mLoop) {
                    iterator = mViews.listIterator();
                    View nextView = mViews.get(0);
                    if(mUseDefaultListener)
                        nextView.setOnClickListener(listener);

                    animator.setOldView(currentView);
                    animator.setNewView(nextView);

                    currentView.startAnimation(animator);
                }
            }
        }
    }

    /**
     * Go back to the previous view
     */
    public void previous()
    {
        if(iterator.hasPrevious())
        {
            View currentView = mViews.get(iterator.nextIndex());

            if(iterator.nextIndex() > 0) {
                View previousView = iterator.previous();

                if(mUseDefaultListener)
                    previousView.setOnClickListener(listener);

                ControllerAnimator animator = getDefaultBackwardAnimation();

                animator.setOldView(currentView);
                animator.setNewView(previousView);
                currentView.startAnimation(animator);
            }

        }
        else
        {
            if(mLoop)
            {
                iterator = mViews.listIterator();
                for(int i = 0; i< mViews.size()-1;i++)
                {
                    iterator.next();
                }

                View currentView = mViews.get(0);
                View previousView = mViews.get(mViews.size()-1);
                if(mUseDefaultListener)
                    previousView.setOnClickListener(listener);

                ControllerAnimator animator = getDefaultBackwardAnimation();

                animator.setOldView(currentView);
                animator.setNewView(previousView);
                currentView.startAnimation(animator);
            }
        }
    }

    /**
     * Go back to the previous view
     * @param animator - the animator to be used for the transition
     */
    public void previous(ControllerAnimator animator)
    {
        if(animator == null)
            throw new NullPointerException("Animator cannot be null");

        if(iterator.hasPrevious())
        {

            View currentView = mViews.get(iterator.nextIndex());

            if(iterator.nextIndex() > 0) {
                View previousView = iterator.previous();

                if(mUseDefaultListener)
                    previousView.setOnClickListener(listener);

                animator.setOldView(currentView);
                animator.setNewView(previousView);
                currentView.startAnimation(animator);
            }

        }
        else
        {
            if(mLoop)
            {
                iterator = mViews.listIterator();
                for(int i = 0; i< mViews.size()-1;i++)
                {
                    iterator.next();
                }

                View currentView = mViews.get(0);
                View previousView = mViews.get(mViews.size()-1);

                if(mUseDefaultListener)
                    previousView.setOnClickListener(listener);

                animator.setOldView(currentView);
                animator.setNewView(previousView);
                currentView.startAnimation(animator);
            }
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
        if(defaultForwardAnimation == null)
            this.mDefaultForwardAnimation = new RightFlipAnimator();
        else
            this.mDefaultForwardAnimation = defaultForwardAnimation;
    }


    /**
     * Set the duration for the default forward animation
     * @param duration - duration in milliseconds
     */
    public void setForwardAnimationDuration(long duration)
    {
        getDefaultForwardAnimation().setDuration(duration);
    }

    /**
     * Set the duration for the default backward animation
     * @param duration - duration in milliseconds
     */
    public void setBackwardAnimationDuration(long duration)
    {
        getDefaultForwardAnimation().setDuration(duration);
    }

    /**
     *
     * @return the next view
     */
    public View getNextView()
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
        if(defaultBackwardAnimation == null)
            this.mDefaultBackwardAnimation = new LeftFlipAnimator();
        else
            this.mDefaultBackwardAnimation = defaultBackwardAnimation;
    }

    /**
     *
     * @return - all the views
     */
    public ArrayList<View> getViews() {
        return mViews;
    }

    /**
     *
     * @param views - the views to be used
     */
    public void setViews(ArrayList<View> views) {
        this.mViews = views;
    }

    /**
     *
     * @return - the iterator being used on the views
     */
    public ListIterator<View> getIterator() {
        return iterator;
    }

    /**
     *
     * @param iterator - the iterator to be used on the views
     */
    public void setIterator(ListIterator<View> iterator) {
        this.iterator = iterator;
    }

    /**
     * Similar to getting the iterator and calling hasNext on it
     * @return if the iterator has a next view
     */
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    /**
     * Similar to getting the iterator and calling hasPrevious on it
     * @return if the iterator has a previous view
     */
    public boolean hasPrevious()
    {
        return iterator.hasPrevious();
    }

    public boolean ismUseDefaultListener() {
        return mUseDefaultListener;
    }

    public void setmUseDefaultListener(boolean mUseDefaultListener) {
        this.mUseDefaultListener = mUseDefaultListener;
    }

    /**
     * Default view listener
     */
    private class ViewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            next();
        }
    }
}
