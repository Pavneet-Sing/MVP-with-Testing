package com.example.pavneet_singh.mvptestingdemo.util;

import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;

/**
 * Created by Pavneet_Singh on 19/1/18.
 */

/**
 * A counter for network delay testing purpose
 */
public class EspressoTestingIdlingResource {
    private static final String RESOURCE = "GLOBAL";

    private static CountingIdlingResource mCountingIdlingResource =
            new CountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }

}
