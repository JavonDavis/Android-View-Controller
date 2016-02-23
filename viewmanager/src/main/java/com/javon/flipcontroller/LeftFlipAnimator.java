package com.javon.flipcontroller;

import android.animation.Animator;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;

/**
 * Class to change one view to the next through a flip animation to the right
 * @author Javon Davis
 *         Created by Javon Davis on 21/02/16.
 */
public class LeftFlipAnimator extends ControllerAnimator {

    /**
     *
     * @param oldView - the view to transition from
     * @param newView - the view to transition to
     */
    public LeftFlipAnimator(View oldView, View newView)
    {
        super(oldView,newView);
    }

    /**
     * Default Constructor
     */
    public LeftFlipAnimator() {
        super();
    }


    @Override
    public void onAnimationStart(Animation animation) {
        final View oldView = getOldView();
        final View newView = getNewView();

        newView.setVisibility(View.INVISIBLE);

        newView.setRotationY(90);

        //clearing the listener is important as it would cause an infinite
        // loop in onAnimationEnd due to the ViewPropertyAnimator in the map created
        oldView.animate().setListener(null).rotationYBy(-90).setInterpolator
                (new AccelerateInterpolator()).setDuration(getDuration());

        Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                newView.setVisibility(View.VISIBLE);
                oldView.setVisibility(View.INVISIBLE);

                //remember to clear the listener
                newView.animate().setListener(null).rotationYBy(-90).setDuration(getDuration());
                oldView.setRotationY(-90);
            }
        }, getDuration()-getDuration()/30); //subtract a small portion to smooth out the transition
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
