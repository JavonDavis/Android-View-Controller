package com.javon.viewmanager.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.javon.flipcontroller.animators.FadingAnimator;
import com.javon.flipcontroller.controllers.Controller;
import com.javon.viewmanager.R;

import java.util.ArrayList;

/**
 * @author Javon Davis
 */
public class ImageViewSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_sample);

        ImageView appleView = (ImageView) findViewById(R.id.apples_imageview);
        ImageView bananaView = (ImageView) findViewById(R.id.banana_imageview);
        ImageView grapesView = (ImageView) findViewById(R.id.grapes_imageview);
        ImageView orangesView = (ImageView) findViewById(R.id.oranges_imageview);

        ArrayList<View> views = new ArrayList<>();
        views.add(appleView);
        views.add(bananaView);
        views.add(grapesView);
        views.add(orangesView);

        Controller controller = new Controller(views,true,false, new FadingAnimator(),new FadingAnimator());
        controller.setControllerListener(new Controller.ControllerListener() {
            @Override
            public void onEndReached() {
                Toast.makeText(ImageViewSampleActivity.this,"No more fruits to show! :(",Toast.LENGTH_LONG).show();
            }
        });

    }
}
