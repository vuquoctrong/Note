package rekkisoft.trongvu.com.note.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.alarm.AlarmActivity;
import rekkisoft.trongvu.com.note.home.HomeActivity;
import rekkisoft.trongvu.com.note.utils.Define;

public class SchedulingService extends IntentService {

    private static final int TIME_VIBRATE = 1000;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SchedulingService(String name) {
        super(SchedulingService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        NotificationManager alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, AlarmActivity.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Wake Up! Wake Up!"))
                .setContentText("Wake Up! Wake Up!");


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }
}
