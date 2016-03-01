package com.javon.viewmanager.examples.samples;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.javon.viewmanager.animators.ControllerAnimator;
import com.javon.viewmanager.controllers.Controller;
import com.javon.viewmanager.examples.R;

import java.util.ArrayList;

/**
 * @author Javon Davis
 */
public class CustomAnimatorSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_animator_sample);

        ImageView appleView = (ImageView) findViewById(R.id.apples_imageview);
        ImageView bananaView = (ImageView) findViewById(R.id.banana_imageview);
        ImageView grapesView = (ImageView) findViewById(R.id.grapes_imageview);
        ImageView orangesView = (ImageView) findViewById(R.id.oranges_imageview);

        ArrayList<View> views = new ArrayList<>();
        views.add(appleView);
        views.add(bananaView);
        views.add(grapesView);
        views.add(orangesView);

        Controller controller = new Controller(this,views,true,true, new ShakeAnimator(this),new ShakeAnimator(this));
        controller.setControllerListener(new Controller.ControllerListener() {
            @Override
            public void onEndReached() {
                Toast.makeText(CustomAnimatorSampleActivity.this,"No more fruits :( But keep going to see them again! :)",Toast.LENGTH_LONG).show();
            }
        });
    }

    private class ShakeAnimator extends ControllerAnimator
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

            Animation shake = AnimationUtils.loadAnimation(CustomAnimatorSampleActivity.this, R.anim.shake);
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
}
