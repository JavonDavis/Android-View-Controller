package com.javon.viewmanager.animators;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.javon.viewmanager.R;

/**
 * Class to change one view to the next through sliding out the first view to the left and instantly showing the new view
 *
 * @author Howard Edwards
 */
public class SlideRightAnimator extends ControllerAnimator {

    public SlideRightAnimator(Context context) {
        super(context);
    }

    public SlideRightAnimator(Context context, View oldView, View newView) {
        super(context, oldView, newView);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        final View oldView = getOldView();
        final View newView = getNewView();

        Animation slide_right = AnimationUtils.loadAnimation(getContext(), R.anim.slide_right);
        slide_right.setDuration(getDuration());
        slide_right.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                oldView.setVisibility(View.GONE);
                newView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        oldView.startAnimation(slide_right);
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
