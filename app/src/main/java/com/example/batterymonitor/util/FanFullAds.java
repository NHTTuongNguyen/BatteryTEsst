package com.example.batterymonitor.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import static android.content.ContentValues.TAG;


public class FanFullAds {
    private static FanFullAds singleton;
    private InterstitialAd interstitialAd;
    private boolean isReloaded;
    private boolean loadNow = false;
    public static FanFullAds getInstance() {
        if (singleton == null)
            singleton = new FanFullAds();
        return singleton;
    }

    public void showAds() {
        if (interstitialAd != null) {
            if (interstitialAd.isAdLoaded() && !interstitialAd.isAdInvalidated()) {
                interstitialAd.show();
            }
            else {
                loadNow = true;
                loadFullAds();
            }
        }
    }

    public void initInterAds(final Context context) {
        isReloaded = false;
        interstitialAd = new InterstitialAd(context, "VID_HD_9_16_39S_APP_INSTALL#789352421869208_789371015200682");
        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
                loadFullAds();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                if (loadNow) {
                    interstitialAd.show();
                    loadNow = false;
                }
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        });
        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd();

    }
    private void loadFullAds() {
        isReloaded = true;
        interstitialAd.loadAd();
    }

    public void showAdWithDelay() {
        /**
         * Here is an example for displaying the ad with delay;
         * Please do not copy the Handler into your project
         */
        // Handler handler = new Handler();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // Check if interstitialAd has been loaded successfully
                if(interstitialAd == null || !interstitialAd.isAdLoaded()) {
                    return;
                }
                // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
                if(interstitialAd.isAdInvalidated()) {
                    loadNow = true;
                    loadFullAds();
                    return;
                }
                // Show the ad
                interstitialAd.show();
            }
        }, 1000 * 60 * 15); // Show the ad after 15 minutes
    }
    public void onDestroy() {
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
    }
}
