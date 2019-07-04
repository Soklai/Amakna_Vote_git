package com.onizia.amaknavote;

import android.app.*;
import android.os.*;
import android.webkit.*;
import android.widget.*;
import java.net.*;
import android.view.*;
import android.content.SharedPreferences;
import android.preference.*;
import java.util.prefs.*;
import android.content.Intent;
import java.util.*;
import android.util.*;
import android.content.*;
import android.support.v4.app.*;

public class NotificationWakeup extends Activity
{
	private View view;
	private static final String CHANNEL_ID = "CHANNEL_ID";
    private int notificationId = 1500;
	
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		
        createNotificationChannel();
		showNotification(view);
		
		//Intent I_MainActivity = new Intent().setClass(this, MainActivity.class);
		//startActivity(I_MainActivity);
		
		this.finish();
    }
	
	private void createNotificationChannel() 
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) 
		{
			CharSequence name = "Notification channel name";
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
			channel.setDescription("Notification channel description");
			NotificationManager notificationManager = getSystemService(NotificationManager.class);
			Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
		}
	}

	public void showNotification(View view)
	{
		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.icon)
			.setContentTitle("! VOTE DISPONIBLE !")
			.setContentText("Vous pouvez des à présents voter")
			.setPriority(NotificationCompat.PRIORITY_DEFAULT)
		    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

		notificationManager.notify(notificationId, notifBuilder.build());
	}
}
