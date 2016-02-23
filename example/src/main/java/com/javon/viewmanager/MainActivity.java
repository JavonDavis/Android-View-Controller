package com.javon.viewmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.javon.viewmanager.samples.CustomAnimatorSampleActivity;
import com.javon.viewmanager.samples.ImageViewSampleActivity;
import com.javon.viewmanager.samples.RecyclerViewSampleActivity;
import com.javon.viewmanager.samples.TextViewSampleActivity;
import com.javon.viewmanager.samples.ViewGroupSampleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button textViewSampleButton = (Button) findViewById(R.id.textview_sample);
        Button imageViewSampleButton = (Button) findViewById(R.id.imageview_sample);
        Button viewGroupSampleButton = (Button) findViewById(R.id.viewgroup_sample);
        Button customAnimatorSampleButton = (Button) findViewById(R.id.animator_sample);
        Button recyclerViewSampleButton = (Button) findViewById(R.id.recyclerview_sample);

        textViewSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TextViewSampleActivity.class);
                startActivity(intent);
            }
        });

        imageViewSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImageViewSampleActivity.class);
                startActivity(intent);
            }
        });

        viewGroupSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewGroupSampleActivity.class);
                startActivity(intent);
            }
        });

        customAnimatorSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomAnimatorSampleActivity.class);
                startActivity(intent);
            }
        });

        recyclerViewSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewSampleActivity.class);
                startActivity(intent);
            }
        });

    }
}
