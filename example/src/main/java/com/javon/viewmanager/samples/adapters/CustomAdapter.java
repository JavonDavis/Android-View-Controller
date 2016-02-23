package com.javon.viewmanager.samples.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javon.flipcontroller.controllers.Controller;
import com.javon.viewmanager.R;

import java.util.ArrayList;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 23/02/16.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder> {

    private ArrayList<String> words;
    private Context context;

    public CustomAdapter(Context context, ArrayList<String> words)
    {
        this.context = context;
        this.words = words;
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        String word = words.get(position);
        holder.large.setText(word);
        holder.small.setText(word);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder
    {

        public TextView small;
        public TextView large;
        public Controller controller;

        public CustomHolder(View itemView) {
            super(itemView);
            small = (TextView) itemView.findViewById(R.id.smallView);
            large = (TextView) itemView.findViewById(R.id.largeView);

            ArrayList<View> views = new ArrayList<>();
            views.add(large);
            views.add(small);

            controller = new Controller(views,true,true);

        }

    }
}
