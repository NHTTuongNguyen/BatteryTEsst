package com.example.batterymonitor.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.batterymonitor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

public class AdmobNative {
    private static AdmobNative singleton;
    private AdView adView;

    public static AdmobNative getInstance() {
        if (singleton == null)
            singleton = new AdmobNative();
        return singleton;
    }
//
//    public void initBannerAds(final Context context) {
//        final LinearLayout adContainer = (LinearLayout) ((Activity) context).findViewById(R.id.banner_container);
//        adContainer.setVisibility(View.VISIBLE);
//        adView = ((Activity) context).findViewById(R.id.adView);
//        adView.setVisibility(View.VISIBLE);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//        adView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//            }
//            @Override
//            public void onAdFailedToLoad(LoadAdError loadAdError) {
//                super.onAdFailedToLoad(loadAdError);
//                adContainer.removeAllViewsInLayout();
//                adContainer.addView(FanBanner.getInstance().initInterAds(context));
//            }
//        });
//        AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
//                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//                    @Override
//                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd ad) {
//                        if (((Activity) context).isDestroyed()) {
//                            ad.destroy();
//                            return;
//                        }
//                    }
//                }).build();
//    }
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
    }
    public void onResume() {
        if (adView != null) {
            adView.resume();
        }
    }
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
    }
}
