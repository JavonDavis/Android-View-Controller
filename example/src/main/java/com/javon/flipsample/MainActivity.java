package com.javon.flipsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.javon.flipcontroller.Controller;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout img1 = (LinearLayout) findViewById(R.id.blue);
        final LinearLayout img2 = (LinearLayout) findViewById(R.id.red);

        ArrayList<View> views = new ArrayList<>();
        views.add(img1);
        views.add(img2);

        controller = new Controller(views);

    }

    @Override
    public void onBackPressed() {
        controller.previous();
    }
}
