package com.javon.flipcontroller;

import android.animation.Animator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 21/02/16.
 */
public class RightFlipAnimation extends Animation implements Animation.AnimationListener {

    private static final int DEFAULT_DURATION = 1000;

    private View mOldView;
    private View mNewView;

    public RightFlipAnimation(View oldView, View newView)
    {
        this.mOldView = oldView;
        this.mNewView = newView;
        this.mNewView.setLayoutParams(this.mOldView.getLayoutParams());
        setDuration(DEFAULT_DURATION);
        setAnimationListener(this);
    }


    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("Animation","Started");
        mNewView.setRotationY(-90);

        //clearing the listener is important as it would cause an infinite
        // loop in onAnimationEnd due to the ViewProperityAnimator in the map created
        mOldView.animate().setListener(null).rotationYBy(90).setInterpolator
                (new AccelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("Inner Animation","Started");
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                mNewView.setVisibility(View.VISIBLE);
                mOldView.setVisibility(View.INVISIBLE);

                //remember to clear the listener
                mNewView.animate().setListener(null).rotationYBy(90).setDuration(getDuration());
                mOldView.setRotationY(180);
                Log.d("Inner Animation","Ended");

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).setDuration(getDuration());
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
