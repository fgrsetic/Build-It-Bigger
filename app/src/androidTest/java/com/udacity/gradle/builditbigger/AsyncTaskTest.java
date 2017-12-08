package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Franjo on 6.12.2017..
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    private EndpointAsyncTask task;

    @Test
    public void testNonEmptyString() {
        task = new EndpointAsyncTask(new EndpointAsyncTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    output = task.get(30, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    e.printStackTrace();
                }

                assertThat(output, notNullValue());
                assertTrue(output.length() > 0);
            }
        });

        task.execute();

    }
}
