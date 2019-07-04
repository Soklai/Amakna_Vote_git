package com.onizia.amaknavote;
import android.app.*;
import android.app.*;
//import android.app.AlarmManager;
import android.os.*;
import android.webkit.*;
import android.widget.*;
import java.net.*;
import android.view.*;
import android.preference.*;
import java.util.prefs.*;
import android.content.Intent;
import java.util.*;
import android.util.*;
import android.content.*;

public class AlarmWakeup extends Activity
{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		Alarm();
		this.finish();
    }
	
	public void Alarm()
	{
		//Intent i = new Intent(getApplicationContext(), MainActivity.class);
		Intent i = new Intent().setClass(this, NotificationWakeup.class);
		PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),3333,i, PendingIntent.FLAG_CANCEL_CURRENT);
        
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 10);
		cal.add(Calendar.MINUTE, 0);
		cal.add(Calendar.HOUR, 0);
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), pi);

	}

	/*public void alarmWakeup()
	{
		Vibrator v = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);

		Toast.makeText(this, "Alarm Triggered", Toast.LENGTH_SHORT).show();
		v.vibrate(1000);
	}*/
}
