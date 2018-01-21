package com.example.pavneet_singh.mvptestingdemo.model;

import android.os.Handler;
import android.os.SystemClock;

import com.example.pavneet_singh.mvptestingdemo.model.Movie;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.example.pavneet_singh.mvptestingdemo.model.Movie.Type;
import com.example.pavneet_singh.mvptestingdemo.mvp.movieslist.MoviesListContract;
import com.example.pavneet_singh.mvptestingdemo.util.Utility;

/**
 * Created by Pavneet Singh on 1/19/2018.
 */

/* class to demonstrate network calls responses
* */
public final class SimulateMovieClient implements MovieRepo{

    static final Random RANDOM = new Random();


    public void getMovieList(final MoviesListContract.OnResponseCallback callback)  {
        // To imitate network request delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Movie> movies = new ArrayList<>();
                try {
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"IT", Utility.convertStringToDate("2017-10-8"),7.6,127,Type.HORROR));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"Jumanji 2", Utility.convertStringToDate("2018-12-20"),8.3,111,Type.ACTION));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"The Dark Knight", Utility.convertStringToDate("2008-07-08"),9.0,152,Type.ACTION));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"Inception", Utility.convertStringToDate("2010-07-16"),8.8,148,Type.ACTION));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"The Green Mile", Utility.convertStringToDate("1999-12-10"),8.5,189,Type.DRAMA));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"Transcendence", Utility.convertStringToDate("2014-04-18"),6.3,120,Type.FICTION));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"Saving Private Ryan", Utility.convertStringToDate("1998-07-24"),8.6,169,Type.DRAMA));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"Whiplash", Utility.convertStringToDate("2015-02-20"),8.5,117,Type.DRAMA));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"The Raid", Utility.convertStringToDate("2011-04-13"),7.6,111,Type.ACTION));
                    movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE),"Burnt", Utility.convertStringToDate("2015-10-30"),6.6,111,Type.DRAMA));
                    callback.onResponse(movies);
                } catch (ParseException e) {
                    callback.onError("Error from network");
                }
            }
        }, 1500);

    }
}
