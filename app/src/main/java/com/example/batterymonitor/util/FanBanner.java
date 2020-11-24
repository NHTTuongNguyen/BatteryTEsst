package com.example.batterymonitor.util;

import android.content.Context;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

public class FanBanner {
    private static FanBanner singleton;
    private AdView adView;

    public static FanBanner getInstance() {
        if (singleton == null)
            singleton = new FanBanner();
        return singleton;
    }

    public AdView initInterAds(final Context context ) {
        adView = new com.facebook.ads.AdView(context, "IMG_16_9_APP_INSTALL#789352421869208_789369161867534", AdSize.BANNER_HEIGHT_50);
        // Request an ad
        adView.loadAd();
        return adView;
    }

    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
    }
}
