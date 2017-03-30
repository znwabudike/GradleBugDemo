package com.drawingboardapps.gradlebugdemo;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by zach on 3/29/17.
 */

public class MyApplication extends MultiDexApplication{
    private static final String TWITTER_KEY = "HBvNolZUPaCWCLXvVlbEarQgC";
    private static final String TWITTER_SECRET = "TbBMBQFKNR72QXFrPusMJKZVle8LAUghASrSbXYxEiaG2eGTk5";
    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate called");
        setupFabric();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void setupFabric() {
        Log.e(TAG, "Setting up Fabric");
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        TwitterCore twitterCore = new TwitterCore(authConfig);

        Digits digitsKit = new Digits.Builder()
                .build();

        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder()
                        .disabled(BuildConfig.DEBUG)
                        .build())
                .build();

        Fabric fabric = new Fabric.Builder(getApplicationContext())
                .kits(twitterCore, digitsKit, crashlyticsKit)
                .build();

        Fabric.with(fabric);
    }
}
