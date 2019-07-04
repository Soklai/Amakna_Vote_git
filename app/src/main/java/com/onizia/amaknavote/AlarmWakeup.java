package com.onizia.amaknavote;
import android.app.*;
import android.os.*;
import android.content.Intent;
import java.util.*;

public class AlarmWakeup extends Activity
{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		Alarm();
		this.finish();
    }
	
	public void Alarm()
	{
		Intent i = new Intent().setClass(this, NotificationWakeup.class);
		PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),3333,i, PendingIntent.FLAG_CANCEL_CURRENT);
        
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 10);
		cal.add(Calendar.MINUTE, 0);
		cal.add(Calendar.HOUR, 0);
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), pi);
	}
}
