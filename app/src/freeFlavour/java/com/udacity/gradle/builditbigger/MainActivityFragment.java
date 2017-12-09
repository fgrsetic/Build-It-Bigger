package com.udacity.gradle.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.jokedisplay.JokeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment implements OnAsyncReceiveListener {

    private static final String JOKE_KEY = "Joke key";

    private ProgressBar mSpinner;
    private InterstitialAd mInterstitialAd;
    private String mResult;


    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        mSpinner = fragmentView.findViewById(R.id.progressBar);
        Button btnJoke = fragmentView.findViewById(R.id.btnJoke);

        // Interstitial ads are requested and shown by InterstitialAd objects
        mInterstitialAd = new InterstitialAd(getContext());
        // This test ad unit ID for Android has been specially configured to return test ads for every request,
        // and it is free to use it in apps while coding, testing, and debugging.
        // Just make sure you replace it with your own ad unit ID before publishing your app
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        // Make all calls to the Mobile Ads SDK on the main thread
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchJoke();
            }
        });


        mInterstitialAd.setAdListener(new AdListener() {

            // Code to be executed when an ad finishes loading
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();
                mInterstitialAd.loadAd(adRequest);
            }

            // Code to be executed when when the interstitial ad is closed
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startJokeDisplayActivity();
            }
        });


        loadBannerAd(fragmentView);


        return fragmentView;
    }

    private void loadBannerAd(View fragmentView) {
        AdView mAdView = fragmentView.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void fetchJoke() {
        mSpinner.setVisibility(View.VISIBLE);
        new EndpointAsyncTask().execute(this);
    }

    @Override
    public void onReceive(String output) {
        mResult = output;
        mSpinner.setVisibility(View.INVISIBLE);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startJokeDisplayActivity();
        }

    }

    private void startJokeDisplayActivity() {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JOKE_KEY, mResult);
        startActivity(intent);
    }
}
