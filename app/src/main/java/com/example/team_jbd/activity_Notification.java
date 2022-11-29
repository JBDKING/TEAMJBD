package com.example.team_jbd;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

// 알림 기능
public class activity_Notification extends ContextWrapper
{
    public static final String channelID = "channelID";
    public static final String channelNm = "channelNm";

    private  NotificationManager notiManager;

    public activity_Notification(Context base)
    {
        super(base);
    }

    public void createChannels()
    {
        NotificationChannel channel1 = new NotificationChannel(channelID, channelNm, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor((com.google.android.material.R.color.design_default_color_on_primary));
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);
    }

    public  NotificationManager getManager()
    {
        if(notiManager == null)
        {
            notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notiManager;
    }

    public NotificationCompat.Builder getChannelNotification()
    {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("JBD")
                .setContentText("그릇을 깨끗이 씻어주세요!")
                .setSmallIcon(R.drawable.ic_launcher_background);
    }
}
