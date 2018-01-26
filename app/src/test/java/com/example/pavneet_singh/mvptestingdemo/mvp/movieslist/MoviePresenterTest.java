package com.example.pavneet_singh.mvptestingdemo.mvp.movieslist;

import android.support.test.espresso.IdlingRegistry;

import com.example.pavneet_singh.mvptestingdemo.model.Movie;
import com.example.pavneet_singh.mvptestingdemo.model.MovieRepo;
import com.example.pavneet_singh.mvptestingdemo.util.EspressoTestingIdlingResource;
import com.example.pavneet_singh.mvptestingdemo.model.SimulateMovieClient;
import com.example.pavneet_singh.mvptestingdemo.util.Utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Pavneet_Singh on 1/21/18.
 */
public class MoviePresenterTest {

    private static final Random RANDOM = new Random();

    @Mock
    private MoviesListContract.View view;

    @Mock
    private MovieRepo movieRepo;

    private MoviePresenter presenter;


    @Captor
    private ArgumentCaptor<MoviesListContract.OnResponseCallback> argumentCaptor;

    @Before
    public void setUp(){
        // A convenient way to inject mocks by using the @Mock annotation in Mockito.
        //  For mock injections , initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // get the presenter reference and bind with view for testing
        presenter = new MoviePresenter(view,movieRepo);


    }

    @Test
    public void loadMoviewList() throws Exception {
        presenter.loadMoviewList();
        verify(movieRepo,times(1)).getMovieList(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(getList());
        verify(view,times(1)).hideProgress();
        ArgumentCaptor<List> entityArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).showMovieList(entityArgumentCaptor.capture());

        assertTrue(entityArgumentCaptor.getValue().size() == 10);
    }

    @Test
    public void OnError() throws Exception {
        presenter.loadMoviewList();
        verify(movieRepo,times(1)).getMovieList(argumentCaptor.capture());
        argumentCaptor.getValue().onError("Error");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(view,times(1)).showLoadingError(argumentCaptor.capture());
        verify(view).showLoadingError(argumentCaptor.getValue());
    }

    private List<Movie> getList() {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "IT", Utility.convertStringToDate("2017-10-8"), 7.6, 127, Movie.Type.HORROR));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "Jumanji 2", Utility.convertStringToDate("2018-12-20"), 8.3, 111, Movie.Type.ACTION));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "The Dark Knight", Utility.convertStringToDate("2008-07-08"), 9.0, 152, Movie.Type.ACTION));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "Inception", Utility.convertStringToDate("2010-07-16"), 8.8, 148, Movie.Type.ACTION));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "The Green Mile", Utility.convertStringToDate("1999-12-10"), 8.5, 189, Movie.Type.DRAMA));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "Transcendence", Utility.convertStringToDate("2014-04-18"), 6.3, 120, Movie.Type.FICTION));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "Saving Private Ryan", Utility.convertStringToDate("1998-07-24"), 8.6, 169, Movie.Type.DRAMA));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "Whiplash", Utility.convertStringToDate("2015-02-20"), 8.5, 117, Movie.Type.DRAMA));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "The Raid", Utility.convertStringToDate("2011-04-13"), 7.6, 111, Movie.Type.ACTION));
            movies.add(new Movie(RANDOM.nextInt(Integer.MAX_VALUE), "Burnt", Utility.convertStringToDate("2015-10-30"), 6.6, 111, Movie.Type.DRAMA));
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return movies;
    }
}