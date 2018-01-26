package com.example.pavneet_singh.mvptestingdemo.mvp.movieslist;

import com.example.pavneet_singh.mvptestingdemo.model.MovieRepo;
import com.example.pavneet_singh.mvptestingdemo.util.EspressoTestingIdlingResource;
import com.example.pavneet_singh.mvptestingdemo.model.SimulateMovieClient;
import com.example.pavneet_singh.mvptestingdemo.model.Movie;
import com.example.pavneet_singh.mvptestingdemo.mvp.movieslist.MoviesListContract.OnResponseCallback;
import org.mockito.Mock;
import java.util.List;

/**
 * Created by Pavneet_Singh on 1/19/18.
 */

public final class MoviePresenter implements MoviesListContract.Presenter  {

    private MoviesListContract.View view;

    private MovieRepo mclient;


    public MoviePresenter(MoviesListContract.View view,MovieRepo client) {
        this.view = view;
        mclient = client;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void loadMoviewList() {
        view.showProgress();
        mclient.getMovieList(callback);
        // required for espresso UI testing
        EspressoTestingIdlingResource.increment();
    }

    // callback mechanism , onResponse will be triggered with response
    // by simulatemovieclient(or your network or database process) and pass the response to view
    private final OnResponseCallback callback = new OnResponseCallback() {
        @Override
        public void onResponse(List<Movie> movies) {
            view.showMovieList(movies);
            view.hideProgress();
            EspressoTestingIdlingResource.decrement();
        }

        @Override
        public void onError(String errMsg) {
            view.hideProgress();
            view.showLoadingError(errMsg);
            EspressoTestingIdlingResource.decrement();
        }
    };
}
