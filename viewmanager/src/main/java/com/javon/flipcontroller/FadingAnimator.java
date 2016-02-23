package com.javon.flipcontroller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Animation;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 23/02/16.
 */
public class FadingAnimator extends ControllerAnimator {

    /**
     *
     * @param oldView - the view to transition from
     * @param newView - the view to transition to
     */
    public FadingAnimator(View oldView, View newView)
    {
        super(oldView,newView);
    }

    /**
     * Default Constructor
     */
    public FadingAnimator() {
        super();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        final View oldView = getOldView();
        View newView = getNewView();
        
        // Set the new view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        newView.setAlpha(0f);
        newView.setVisibility(View.VISIBLE);

        // Animate the new view to 100% opacity, and clear any animation
        // listener set on the view.
        newView.animate()
                .alpha(1f)
                .setDuration(getDuration())
                .setListener(null);

        // Animate the old view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        oldView.animate()
                .alpha(0f)
                .setDuration(getDuration())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        oldView.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
