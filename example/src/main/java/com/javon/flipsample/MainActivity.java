package com.javon.flipsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        final TextView hello = (TextView) findViewById(R.id.hello);
        final TextView goodbye = (TextView) findViewById(R.id.goodbye);

        ArrayList<View> views = new ArrayList<>();
        views.add(hello);
        views.add(goodbye);

        FlipController controller = new FlipController(views,true);

    }
}
