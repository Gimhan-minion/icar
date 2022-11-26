package com.uom.icar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Notification_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        List<String> list = new ArrayList<>();
        list.add("Accelerate slowly!");
        list.add("Brake gently!");
        list.add("Maintain a safe speed!");
        list.add("Follow at a reasonable distance!");
        list.add("Be aware of road signs!");
        list.add("Accelerate slowly!");
        list.add("Choose your lane carefully!");


        Random rand = new Random();
        String msg=list.get(rand.nextInt(list.size()));


        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"notify")

                .setSmallIcon(R.drawable.icon)
                .setContentTitle("iCar - Tip!")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(200, builder.build());

    }
}
