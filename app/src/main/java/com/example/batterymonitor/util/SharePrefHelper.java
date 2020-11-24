package com.example.batterymonitor.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SharePrefHelper {
    private static SharePrefHelper singleton;
    private SharedPreferences mSharedPreferences;
    private String sharedPrefFile = "batterymonitor.settings";

    private String FIRST_INSTALL = "first_install";
    private String CURRENT_DATE = "current_date";
    private String COUNT_ADS = "count_ads";

    private String LANGUAGE = "language";

    private SharePrefHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String currentDate = df.format(d);

        if (getCurrentDateSharePref() == null || !getCurrentDateSharePref().equals(currentDate)) {
            saveCurrentDateSharePref(currentDate);
            updateCountingAds(0);
        }
    }

    public static SharePrefHelper getInstance(Context context) {
        if (singleton == null) singleton = new SharePrefHelper(context);
        return singleton;
    }

    public int getCountingAds() {
        return mSharedPreferences.getInt(COUNT_ADS, 0);
    }
    public void updateCountingAds(int count) { mSharedPreferences.edit().putInt(COUNT_ADS, count).apply(); }
    public void updateCountingAds() {
        int countingAds = getCountingAds();
        countingAds++;
        System.out.println("Counting ads: " + countingAds);
        mSharedPreferences.edit().putInt(COUNT_ADS, countingAds).apply();
    }

    public String getCurrentDateSharePref() { return mSharedPreferences.getString(CURRENT_DATE, null); }
    public void saveCurrentDateSharePref(String currentDate) { mSharedPreferences.edit().putString(CURRENT_DATE, currentDate).apply(); }

    public boolean isFirstInstall() {
        return mSharedPreferences.getBoolean(FIRST_INSTALL, true);
    }
    public void saveFirstInstallFlag(boolean flag) { mSharedPreferences.edit().putBoolean(FIRST_INSTALL, flag).apply(); }

    public String getLanguage() { return mSharedPreferences.getString(LANGUAGE, "English"); }
    public void saveLanguage(String time) { mSharedPreferences.edit().putString(LANGUAGE, time).apply(); }


}
