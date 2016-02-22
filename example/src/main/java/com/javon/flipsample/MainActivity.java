package com.javon.flipsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.javon.flipcontroller.FlipController;
import com.javon.flipcontroller.LeftFlipAnimation;
import com.javon.flipcontroller.RightFlipAnimation;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView img1 = (ImageView) findViewById(R.id.imageView);
        final ImageView img2 = (ImageView) findViewById(R.id.imageView2);

        ArrayList<View> views = new ArrayList<>();
        views.add(img1);
        views.add(img2);

        FlipController controller = new FlipController(views);

    }
}
