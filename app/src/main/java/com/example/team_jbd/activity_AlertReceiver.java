package com.example.team_jbd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

// 알람 전달
public class activity_AlertReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        activity_Notification activity_notification = new activity_Notification(context);
        NotificationCompat.Builder nb = activity_notification.getChannelNotification();
        activity_notification.getManager().notify(1, nb.build());
    }
}
