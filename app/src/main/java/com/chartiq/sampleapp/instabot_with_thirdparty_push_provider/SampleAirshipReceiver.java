package com.chartiq.sampleapp.instabot_with_thirdparty_push_provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rokolabs.sdk.analytics.Event;
import com.rokolabs.sdk.analytics.RokoLogger;
import com.urbanairship.AirshipReceiver;
import com.urbanairship.push.PushMessage;


public class SampleAirshipReceiver extends AirshipReceiver {

    private static final String TAG = "SampleAirshipReceiver";

    @Override
    protected void onPushReceived(@NonNull Context context, @NonNull PushMessage message, boolean notificationPosted) {
        Log.i(TAG, "Received push message. Alert: " + message.getAlert() + ". posted notification: " + notificationPosted);

        // log this event to ROKO Mobi
        RokoLogger.addEvents(new Event("received Urban Airship push"));

        // parse out the value for the key 'mobi_instabot_trigger' that was configured in Urban Airship push message
        String instabot_trigger_key = "mobi_instabot_trigger";
        String instabot_trigger_value = message.getPushBundle().getString(instabot_trigger_key);

        // send a specific Mobi event based on the key/value pair attached in the Urban Airship push message
        // each of these Mobi events will be available in the Mobi portal as an option for you to trigger a Mobi InstaBot
        String mobiEventName;

        switch (Integer.parseInt(instabot_trigger_value)) {
            case 1:
                mobiEventName = "InstaBot trigger 1";
                break;

            case 2:
                mobiEventName = "InstaBot trigger 2";
                break;

            case 3:
                mobiEventName = "InstaBot trigger 3";
                break;

            default:
                mobiEventName = "InstaBot trigger - UNDEFINED";
                break;
        }

        RokoLogger.addEvents(new Event(mobiEventName));
        Log.i(TAG, "Sending a Mobi event with the name: " + mobiEventName);
    }


    @Override
    protected void onChannelCreated(@NonNull Context context, @NonNull String channelId) {
        Log.i(TAG, "Channel created. Channel Id:" + channelId + ".");
    }

    @Override
    protected void onChannelUpdated(@NonNull Context context, @NonNull String channelId) {
        Log.i(TAG, "Channel updated. Channel Id:" + channelId + ".");
    }

    @Override
    protected void onChannelRegistrationFailed(Context context) {
        Log.i(TAG, "Channel registration failed.");
    }

    @Override
    protected void onNotificationPosted(@NonNull Context context, @NonNull NotificationInfo notificationInfo) {
        Log.i(TAG, "Notification posted. Alert: " + notificationInfo.getMessage().getAlert() + ". NotificationId: " + notificationInfo.getNotificationId());
    }

    @Override
    protected boolean onNotificationOpened(@NonNull Context context, @NonNull NotificationInfo notificationInfo) {
        Log.i(TAG, "Notification opened. Alert: " + notificationInfo.getMessage().getAlert() + ". NotificationId: " + notificationInfo.getNotificationId());

        // Return false here to allow Urban Airship to auto launch the launcher activity
        return false;
    }

    @Override
    protected boolean onNotificationOpened(@NonNull Context context, @NonNull NotificationInfo notificationInfo, @NonNull ActionButtonInfo actionButtonInfo) {
        Log.i(TAG, "Notification action button opened. Button ID: " + actionButtonInfo.getButtonId() + ". NotificationId: " + notificationInfo.getNotificationId());

        // Return false here to allow Urban Airship to auto launch the launcher
        // activity for foreground notification action buttons
        return false;
    }

    @Override
    protected void onNotificationDismissed(@NonNull Context context, @NonNull NotificationInfo notificationInfo) {
        Log.i(TAG, "Notification dismissed. Alert: " + notificationInfo.getMessage().getAlert() + ". Notification ID: " + notificationInfo.getNotificationId());
    }
}
