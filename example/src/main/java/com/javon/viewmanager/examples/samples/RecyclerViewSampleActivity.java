package com.javon.viewmanager.examples.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.javon.viewmanager.examples.R;
import com.javon.viewmanager.examples.samples.adapters.CustomAdapter;

import java.util.ArrayList;

public class RecyclerViewSampleActivity extends AppCompatActivity {

    private ArrayList<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_sample);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        words = new ArrayList<>();
        populateList();

        CustomAdapter adapter = new CustomAdapter(this,words);
        mRecyclerView.setAdapter(adapter);
    }

    public void populateList()
    {
        words.add("cat");
        words.add("dog");
        words.add("mouse");
        words.add("pear");
        words.add("science");
        words.add("world");
        words.add("peace");
        words.add("hello");
        words.add("jamaica");
        words.add("france");
        words.add("england");
        words.add("spain");
        words.add("spanish");
        words.add("inside");
    }

}
