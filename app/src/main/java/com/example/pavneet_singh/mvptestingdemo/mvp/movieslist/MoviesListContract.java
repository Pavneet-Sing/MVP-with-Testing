package com.example.pavneet_singh.mvptestingdemo.mvp.movieslist;

import com.example.pavneet_singh.mvptestingdemo.model.Movie;

import java.util.List;


/**
 * Created by Pavneet_Singh on 1/19/18.
 */

/**
 * This specifies the contract between the view and the presenter.
 */
public interface MoviesListContract {

    interface View {
        void showProgress();
        void hideProgress();
        void showMovieList(List<Movie> movies);
        void showLoadingError(String errMsg);

    }

    interface Presenter{
        void loadMoviewList();
        void dropView();
    }

    interface OnResponseCallback{
        void onResponse(List<Movie> movies);
        void onError(String errMsg);
    }
}
