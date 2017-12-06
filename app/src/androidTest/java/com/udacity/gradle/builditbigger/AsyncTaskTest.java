package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Franjo on 6.12.2017..
 */

public class AsyncTaskTest extends AndroidTestCase {


    public void testJokeDownload() {

        try {
            EndpointAsyncTask task = new EndpointAsyncTask(new EndpointAsyncTask.AsyncResponse() {
                @Override
                public void processFinish(String output) {

                }
            });

            task.execute();
            String joke = task.get(30, TimeUnit.SECONDS);

            assertThat(joke, notNullValue());
            assertTrue(joke.length() > 0);

        } catch (Exception e) {
            fail("Operation timed out");
        }
    }
}
