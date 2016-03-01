package com.javon.viewmanager.animators;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.javon.viewmanager.R;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 29/02/16.
 */
public class ShakeAnimator extends ControllerAnimator
{

    public ShakeAnimator(Context context) {
        super(context);
    }

    public ShakeAnimator(Context context, View oldView, View newView) {
        super(context, oldView, newView);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        final View oldView = getOldView();
        final View newView = getNewView();

        Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        shake.setDuration(getDuration());
        shake.setAnimationListener(new AnimationListener() {
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
        oldView.startAnimation(shake);
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}