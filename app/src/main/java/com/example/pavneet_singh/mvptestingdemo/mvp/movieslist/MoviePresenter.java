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

    @Mock
    private MoviesListContract.View view;

    private MoviePresenter moviePresenter;

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
        EspressoTestingIdlingResource.increment();
        mclient.getMovieList(callback);
    }

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
