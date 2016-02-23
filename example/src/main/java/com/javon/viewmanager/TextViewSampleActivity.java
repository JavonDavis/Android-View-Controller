package com.javon.viewmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.javon.flipcontroller.controllers.Controller;

import java.util.ArrayList;

public class TextViewSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_sample);

        TextView hi = (TextView) findViewById(R.id.hi);
        TextView my = (TextView) findViewById(R.id.my);
        TextView name = (TextView) findViewById(R.id.name);
        TextView is = (TextView) findViewById(R.id.is);
        TextView _Javon = (TextView) findViewById(R.id.javon);

        ArrayList<View> views = new ArrayList<>();
        views.add(hi);
        views.add(my);
        views.add(name);
        views.add(is);
        views.add(_Javon);

        final Controller controller = new Controller(views,false,true);

        Button nextButton = (Button) findViewById(R.id.next_button);
        Button previousButton = (Button) findViewById(R.id.previous_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.next();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.previous();
            }
        });

    }
}
