package com.javon.viewmanager.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.javon.flipcontroller.controllers.Controller;
import com.javon.viewmanager.R;

import java.util.ArrayList;

/**
 * @author Javon Davis
 */
public class ViewGroupSampleActivity extends AppCompatActivity {

    Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_sample);

        LinearLayout appleLayout = (LinearLayout) findViewById(R.id.apple_layout);
        LinearLayout bananaLayout = (LinearLayout) findViewById(R.id.banana_layout);
        LinearLayout grapesLayout = (LinearLayout) findViewById(R.id.grapes_layout);
        LinearLayout orangeLayout = (LinearLayout) findViewById(R.id.oranges_layout);

        ArrayList<View> views = new ArrayList<>();
        views.add(appleLayout);
        views.add(bananaLayout);
        views.add(grapesLayout);
        views.add(orangeLayout);

        controller = new Controller(views,false,true);
    }

    public void nextFruit(View view) {
        controller.next();
    }
}
