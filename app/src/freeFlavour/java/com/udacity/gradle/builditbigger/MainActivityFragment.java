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

    private static final String TAG = MainActivityFragment.class.getCanonicalName();
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
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchJoke();
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startJokeDisplayActivity();
            }
        });

        loadInterstitialAd();

        AdView mAdView = fragmentView.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        return fragmentView;
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
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
