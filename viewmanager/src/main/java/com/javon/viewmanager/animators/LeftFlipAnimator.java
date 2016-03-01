package com.javon.viewmanager.animators;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
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
    public LeftFlipAnimator(Context context,View oldView, View newView)
    {
        super(context,oldView,newView);
    }

    /**
     * Default Constructor
     */
    public LeftFlipAnimator(Context context) {
        super(context);
    }


    @Override
    public void onAnimationStart(Animation animation) {
        final View oldView = getOldView();
        final View newView = getNewView();

        newView.setVisibility(View.GONE);

        newView.setRotationY(90);

        //clearing the listener is important as it would cause an infinite
        // loop in onAnimationEnd due to the ViewPropertyAnimator in the map created
        oldView.animate().setListener(null).rotationYBy(-90).setInterpolator
                (new AccelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                newView.setVisibility(View.VISIBLE);
                oldView.setVisibility(View.GONE);

                //remember to clear the listener
                newView.animate().setListener(null).rotationYBy(-90).setDuration(getDuration());
                oldView.setRotationY(0);
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
