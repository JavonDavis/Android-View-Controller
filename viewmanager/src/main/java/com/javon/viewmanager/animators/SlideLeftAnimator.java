package com.javon.viewmanager.animators;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.javon.viewmanager.R;

/**
 * Class to change one view to the next through sliding out the first view to the left and instantly showing the new view
 * @author Howard Edwards
 */
public class SlideLeftAnimator extends ControllerAnimator {

    public SlideLeftAnimator(Context context) {
        super(context);
    }

    public SlideLeftAnimator(Context context, View oldView, View newView) {
        super(context, oldView, newView);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        final View oldView = getOldView();
        final View newView = getNewView();

        Animation slide_left = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left);
        slide_left.setDuration(getDuration());
        slide_left.setAnimationListener(new AnimationListener() {
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
        oldView.startAnimation(slide_left);
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}