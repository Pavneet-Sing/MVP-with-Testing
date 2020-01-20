package com.example.pavneet_singh.mvptestingdemo.mvp.movieslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavneet_singh.mvptestingdemo.R;
import com.example.pavneet_singh.mvptestingdemo.util.Utility;
import com.example.pavneet_singh.mvptestingdemo.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavneet Singh on 1/19/2018
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private final Context context;
    private final List<Movie> movStrings = new ArrayList<>();
    private static final String TAG = "MoviesAdapter";

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_model, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {
        Log.e(TAG, "onBindViewHolder: " + position);
        holder.movieTitle.setText(movStrings.get(position).getTitle());
        holder.date.setText(Utility.convertMinutesToDuration(movStrings.get(position).getDurationinMinutes()));
        holder.rating.setText(Double.toString(movStrings.get(position).getRating()));
        holder.moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, movStrings.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setList(List<Movie> list) {
        movStrings.clear();
        movStrings.addAll(list);
        notifyDataSetChanged();
        Log.e(TAG, "onNext: " + movStrings.size());
    }


    @Override
    public int getItemCount() {
        return movStrings.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        TextView date;
        TextView view;
        TextView rating;
        LinearLayout moviesLayout;

        public MovieHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            date = (TextView) v.findViewById(R.id.date);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }
}
