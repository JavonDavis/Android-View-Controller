package com.javon.flipcontroller;

import android.animation.Animator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 21/02/16.
 */
public class RightFlipAnimation extends ControllerAnimator {

    /**
     * Default Constuctor
     */
    public RightFlipAnimation()
    {
        super();
    }

    public RightFlipAnimation(View oldView, View newView)
    {
        super(oldView,newView);
    }


    @Override
    public void onAnimationStart(Animation animation) {
        final View oldView = getOldView();
        final View newView = getNewView();

        newView.setRotationY(-90);

        //clearing the listener is important as it would cause an infinite
        // loop in onAnimationEnd due to the ViewPropertyAnimator in the map created
        oldView.animate().setListener(null).rotationYBy(90).setInterpolator
                (new AccelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                newView.setVisibility(View.VISIBLE);
                oldView.setVisibility(View.GONE);

                //remember to clear the listener
                newView.animate().setListener(null).rotationYBy(90).setDuration(getDuration());
                oldView.setRotationY(180);

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
