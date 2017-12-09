package com.udacity.gradle.jokes;

import java.util.Random;

public class Joker {

    private static final String[] jokes = {
            "Why is it that women find C to be more attractive than Java? Because C doesnt treat them like objects.",
            "Why do Java programmers wear glasses? Because they dont C#!",
            "Why did the programmer quit his job? Because he didnt get arrays."
    };

    public static String getJoke() {
        Random random = new Random();
        int index = random.nextInt(jokes.length);
        return (jokes[index]);
    }
}
