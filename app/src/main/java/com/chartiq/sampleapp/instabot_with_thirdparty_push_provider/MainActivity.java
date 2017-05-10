package com.chartiq.sampleapp.instabot_with_thirdparty_push_provider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rokolabs.sdk.RokoMobi;
import com.urbanairship.UAirship;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init Mobi SDK
        RokoMobi.start(this);

        // init Urban Airship SDK
        UAirship.takeOff(this.getApplication(), new UAirship.OnReadyCallback() {
            @Override
            public void onAirshipReady(UAirship airship) {

                // Enable user notifications
                airship.getPushManager().setUserNotificationsEnabled(true);
            }
        });


    }
}
