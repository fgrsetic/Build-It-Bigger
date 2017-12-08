package com.udacity.gradle.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.jokedisplay.JokeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {

    private static final String JOKE_KEY = "Joke key";
    private ProgressBar mSpinner;


    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        mSpinner = fragmentView.findViewById(R.id.progressBar);

        Button btn = fragmentView.findViewById(R.id.btnJoke);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchJoke();
            }
        });

        return fragmentView;
    }

    public void startJokeActivity(String output) {
        mSpinner.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JOKE_KEY, output);
        startActivity(intent);
    }


    private void fetchJoke() {
        mSpinner.setVisibility(View.VISIBLE);
        new EndpointAsyncTask().execute(new OnAsyncReceiveListener() {
            @Override
            public void onReceive(String output) {
                if(output != null) {
                    startJokeActivity(output);
                } else {
                    Toast.makeText(getContext(), "Error loading joke", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
