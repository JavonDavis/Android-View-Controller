package com.javon.flipcontroller;

import android.view.View;

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

    public Controller(ArrayList<View> views)
    {
        this(views,true);
    }

    /**
     *
     * @param views - list of views to be used in the correct order
     * @param useDefaultListeners - if true, the default onlick listener will be used which is an onClick listener on the entire view
     */
    public Controller(ArrayList<View> views, boolean useDefaultListeners)
    {
        this(views,useDefaultListeners,null,null,true);
    }

    /**
     *
     * @param views - list of views to be used in the correct order
     * @param useDefaultListeners - if true, the default onlick listener will be used which is an onClick listener on the entire view
     * @param defaultForwardAnimation - animation to be used whenever going to the next view in the list, if null the default is used
     * @param defaultBackwardAnimation - animation to be used whenever going back to the previous view in the list, if null the default is used
     * @param loop - if true it will loop around the list of views and start from the beginning
     */
    public Controller(ArrayList<View> views, boolean useDefaultListeners, ControllerAnimator defaultForwardAnimation, ControllerAnimator defaultBackwardAnimation, boolean loop) {
        this.mViews = views;
        this.iterator = views.listIterator();

        listener = new ViewListener();

        if(useDefaultListeners)
            views.get(0).setOnClickListener(listener);

        this.mLoop = loop;

        if(defaultForwardAnimation == null)
            this.mDefaultForwardAnimation = new RightFlipAnimation();
        else
            this.mDefaultForwardAnimation = defaultForwardAnimation;

        if(defaultBackwardAnimation == null)
            this.mDefaultBackwardAnimation = new LeftFlipAnimation();
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
            currentView.setOnClickListener(null);

            if (iterator.nextIndex() < mViews.size()) {
                View nextView = mViews.get(iterator.nextIndex());
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
            currentView.setOnClickListener(null);

            if (iterator.nextIndex() < mViews.size()) {
                View nextView = mViews.get(iterator.nextIndex());
                nextView.setOnClickListener(listener);
                animator.setOldView(currentView);
                animator.setNewView(nextView);

                currentView.startAnimation(animator);

            } else {
                if (mLoop) {
                    iterator = mViews.listIterator();
                    View nextView = mViews.get(0);
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
                previousView.setOnClickListener(listener);

                ControllerAnimator animator = getDefaultBackwardAnimation();

                animator.setOldView(currentView);
                animator.setNewView(previousView);
                currentView.startAnimation(animator);
            }
        }
    }

    public void previous(ControllerAnimator animator)
    {
        if(animator == null)
            throw new NullPointerException("Animator cannot be null");

        if(iterator.hasPrevious())
        {

            View currentView = mViews.get(iterator.nextIndex());

            if(iterator.nextIndex() > 0) {
                View previousView = iterator.previous();

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
                previousView.setOnClickListener(listener);

                animator.setOldView(currentView);
                animator.setNewView(previousView);
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
            view.setOnClickListener(listener);
        }
    }

    /**
     * remove onClicklistener on all the views
     */
    private void removeListeners() {
        for(View view:mViews)
        {
            view.setOnClickListener(null);
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
            this.mDefaultForwardAnimation = new RightFlipAnimation();
        else
            this.mDefaultForwardAnimation = defaultForwardAnimation;
    }



    public void setForwardAnimationDuration(long duration)
    {
        getDefaultForwardAnimation().setDuration(duration);
    }

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
            this.mDefaultBackwardAnimation = new LeftFlipAnimation();
        else
            this.mDefaultBackwardAnimation = defaultBackwardAnimation;
    }

    public ArrayList<View> getViews() {
        return mViews;
    }

    public void setViews(ArrayList<View> views) {
        this.mViews = views;
    }

    public ListIterator<View> getIterator() {
        return iterator;
    }

    public void setIterator(ListIterator<View> iterator) {
        this.iterator = iterator;
    }

    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    public boolean hasPrevious()
    {
        return iterator.hasPrevious();
    }

    private class ViewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            next();
        }
    }

    private class TestViewListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            previous();
            return false;
        }
    }
}
