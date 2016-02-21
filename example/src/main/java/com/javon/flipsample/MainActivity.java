package com.javon.flipsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.javon.flipcontroller.LeftFlipAnimation;
import com.javon.flipcontroller.RightFlipAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView hello = (TextView) findViewById(R.id.hello);
        final TextView goodbye = (TextView) findViewById(R.id.goodbye);

        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RightFlipAnimation animation = new RightFlipAnimation(hello,goodbye);
                hello.startAnimation(animation);
            }
        });

        goodbye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeftFlipAnimation animation = new LeftFlipAnimation(goodbye,hello);
                goodbye.startAnimation(animation);
            }
        });

    }
}
