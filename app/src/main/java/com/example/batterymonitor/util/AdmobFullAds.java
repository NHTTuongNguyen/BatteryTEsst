package com.example.batterymonitor.util;

import android.content.Context;

import com.example.batterymonitor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;


public class AdmobFullAds {
    private static AdmobFullAds singleton;
    private InterstitialAd interstitialAd;
    //    private String interVideoAds = "ca-app-pub-3940256099942544/8691691433";
    private String interAds = "ca-app-pub-3940256099942544/1033173712";
    private boolean isReloaded;
    Context context;
    public static AdmobFullAds getInstance() {
        if (singleton == null)
            singleton = new AdmobFullAds();
        return singleton;
    }

    public void showAds() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            SharePrefHelper sharePrefHelper = SharePrefHelper.getInstance(context);
            interstitialAd.show();
            sharePrefHelper.updateCountingAds();
        }

    }

    public void initInterAds(final Context context) {
        this.context = context;
        isReloaded = false;
        interAds = context.getString(R.string.interstitial_full_screen);
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(interAds);

        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (!isReloaded) {
                    showAds();
                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                FanFullAds.getInstance().showAds();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                loadFullAds();
            }
        });
    }
//    public void initInterVideoAds(final Context context) {
//        interstitialAd = new InterstitialAd(context);
//        interstitialAd.setAdUnitId(interVideoAds);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        interstitialAd.loadAd(adRequest);
//
//    }

    private void loadFullAds() {
        isReloaded = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }

}
