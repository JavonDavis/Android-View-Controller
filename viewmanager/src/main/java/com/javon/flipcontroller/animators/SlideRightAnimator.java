package com.javon.flipcontroller.animators;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;

/**
 * Class to change one view to the next through sliding out the first view to the left and instantly showing the new view
 * @author Howard Edwards
 */
public class SlideRightAnimator extends ControllerAnimator {

    public SlideRightAnimator() {
        super();
    }

    public SlideRightAnimator(View oldView, View newView) {
        super(oldView, newView);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        final View oldView = getOldView();
        final View newView = getNewView();

        newView.setVisibility(View.GONE);

        newView.setTranslationX(-newView.getWidth());

        //clearing the listener is important as it would cause an infinite
        // loop in onAnimationEnd due to the ViewPropertyAnimator in the map created
        oldView.animate().setListener(null).translationXBy(oldView.getWidth()).setInterpolator
                (new AccelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                newView.setVisibility(View.VISIBLE);
                oldView.setVisibility(View.GONE);

                //remember to clear the listener
                newView.animate().setListener(null).translationXBy(newView.getWidth()).setDuration(getDuration());
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
