package com.example.pavneet_singh.mvptestingdemo.mvp.movieslist;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavneet_singh.mvptestingdemo.R;
import com.example.pavneet_singh.mvptestingdemo.model.Movie;
import com.example.pavneet_singh.mvptestingdemo.model.SimulateMovieClient;

import java.util.List;

import static android.support.v4.widget.SwipeRefreshLayout.*;

/**
 * Created by Pavneet_Singh on 1/19/18.
 */

public class MoviesListActivity extends AppCompatActivity implements MoviesListContract.View{

    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private SwipeRefreshLayout swipeLayout;
    private MoviesListContract.Presenter presenter;
    private TextView tv_empty_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        initViews();
    }

    private void initViews(){
        presenter = new MoviePresenter(MoviesListActivity.this, new SimulateMovieClient());
        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_list); // list
        tv_empty_msg = (TextView)findViewById(R.id.swipe_msg_tv); // empty message
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // for vertical liner list
        moviesAdapter = new MoviesAdapter(this);
        recyclerView.setAdapter(moviesAdapter);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(listener);
        swipeLayout.setColorSchemeColors( // colors for progress dialog
                ContextCompat.getColor(MoviesListActivity.this, R.color.colorPrimary),
                ContextCompat.getColor(MoviesListActivity.this, R.color.colorAccent),
                ContextCompat.getColor(MoviesListActivity.this, android.R.color.holo_green_light)
        );
    }

    private final OnRefreshListener listener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            presenter.loadMoviewList();
        }
    };

    // for future, to show progress
    @Override
    public void showProgress() {
        swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeLayout.setRefreshing(false);
    }

    // toggle the visibility of empty textview or list
    // display list only when response it not empty
    private void showORHideListView(boolean flag){
        if (flag){
            tv_empty_msg.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }else {
            tv_empty_msg.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMovieList(List<Movie> movies) {
        if (!movies.isEmpty()){
            moviesAdapter.setList(movies);
            showORHideListView(true);
        }
    }

    @Override
    public void showLoadingError(String errMsg) {
        hideProgressAndShowErr(errMsg);
        showORHideListView(false);
    }

    private void hideProgressAndShowErr(String msg){
        tv_empty_msg.setVisibility(View.VISIBLE);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        showORHideListView(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}