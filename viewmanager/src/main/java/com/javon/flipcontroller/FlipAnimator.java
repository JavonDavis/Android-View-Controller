package com.javon.flipcontroller;

import android.animation.Animator;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 8/5/15.
 */
public class FlipAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateChange(final RecyclerView.ViewHolder oldHolder,
                                 final RecyclerView.ViewHolder newHolder,
                                 int fromX, int fromY, int toX, int toY) {
        Log.d("change animation", "animateChange, old item: ${oldHolder?.position}, " +
                "new item: ${newHolder?.position}, from: (${[fromX, fromY]}), to: (${[toX, toY]})");

        //TODO - review this initial attempt maybe its more efficient
        //dispatchChangeStarting(oldHolder,false);
        //boolean animateChange = false; //super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY)
//        ViewCompat.setAlpha(oldHolder.itemView,0);
////        ViewCompat.setAlpha(newHolder.itemView, 0);
////        ViewCompat.animate(newHolder.itemView).rotationY(90).start();
//
//        ViewCompat.animate(oldHolder.itemView).rotationYBy(90).start();
//        //ViewCompat.animate(oldHolder.itemView).rotationY(0).start();
//        //ViewCompat.setAlpha(oldHolder.itemView, 1);
//
//        ViewCompat.setAlpha(newHolder.itemView, 1);
//        ViewCompat.animate(newHolder.itemView).rotationY(90).start();
        //ViewCompat.animate(oldHolder.itemView).rotationY(0).start();
        //ViewCompat.setAlpha(newHolder.itemView, 1);
        //dispatchChangeFinished(newHolder,true);

        //This flip implementation is cleaner than the results the above attempt was displaying
        //TODO - need to ensure views are recycled properly
        newHolder.itemView.setRotationY(-90);
        //ViewCompat.animate(newHolder.itemView).rotationY(-90);
        oldHolder.itemView.animate().rotationYBy(90).setInterpolator
                (new AccelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                newHolder.itemView.setVisibility(View.VISIBLE);
                oldHolder.itemView.setVisibility(View.INVISIBLE);
                newHolder.itemView.animate().rotationYBy(90).setDuration(100);
                oldHolder.itemView.setRotationY(180);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).setDuration(100);



        return true;
    }
}
