package com.example.pavneet_singh.mvptestingdemo.model;

import com.example.pavneet_singh.mvptestingdemo.mvp.movieslist.MoviesListContract;

/**
 * Created by Pavneet_Singh on 1/21/18.
 */

public interface MovieRepo {
    void getMovieList(MoviesListContract.OnResponseCallback callback);
}
