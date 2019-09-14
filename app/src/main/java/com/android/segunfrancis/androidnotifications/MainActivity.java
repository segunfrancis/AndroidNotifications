package com.android.segunfrancis.androidnotifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    final String CHANNEL_ID = "android 28 id";
    final int NOTIFICATION_ID = 2;
    final String CHANNEL_NAME = "android_O";
    private NotificationManagerCompat mNotificationManagerCompat;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_icon);

        createNotificationChannel();

        findViewById(R.id.basic_notification).setOnClickListener(view -> {
            createNotificationChannel();
            createBasicNotification();
        });

        findViewById(R.id.bigText_notification).setOnClickListener(view -> {
            createNotificationChannel();
            createBigTextNotification();
        });

        findViewById(R.id.large_image_notification).setOnClickListener(view -> {
            createNotificationChannel();
            createLargeImageNotification();
        });
    }

    private void createBasicNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("This is our title")
                .setContentText("This is the content of our basic notification")
                .setLargeIcon(mBitmap)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        mNotificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        mNotificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private void createBigTextNotification() {
        // Create an explicit intent
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("This is our title")
                .setContentText("This is the content of our basic notification")
                .setLargeIcon(mBitmap)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("This is a big text that has more information than the previous texts"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        mNotificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        mNotificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private void createLargeImageNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Mark Henry")
                .setContentText("I was selected for the phase 2 of the ALC program")
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(mBitmap))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Issue the notification
        mNotificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        mNotificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Android O notification channel");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
