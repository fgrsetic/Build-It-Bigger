package com.udacity.gradle.jokes.test;

import com.udacity.gradle.jokes.Joker;

import org.junit.Test;

public class JokerTest {
    @Test
    public void test() {
        assert Joker.getJoke().length() != 0;
    }
}
