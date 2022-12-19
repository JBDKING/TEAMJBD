package com.example.team_jbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.DateFormat;
import java.util.Calendar;


public class activity_Seting extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener
{
    public static final String TAG = "SETTING";

    private TextView time_text;

    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_seting);

        time_text = findViewById(R.id.time_btn);

        Button time_btn = findViewById(R.id.time_btn);

        // 시간
        time_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DialogFragment timePicker = new activity_TimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        // 알람 취소
        Button alarm_cancel_btn = findViewById(R.id.alarm_cancel_btn);
        alarm_cancel_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cancelAlarm();
            }
        });
    }

    // 시간 정하고 메소드 호출

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        Log.d(TAG, "## onTimeSet ##");
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        //시간지정
        updateTimeText(c);

        //알람설정
        startAlarm(c);
    }

    // 시간선택 후 보여주는 메소드
    private void updateTimeText(Calendar c)
    {
        Log.d(TAG, "## updateTimeText ##");
        String timeText = "알람시간 : ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        time_text.setText(timeText);
    }

    //알람시작
    private void startAlarm(Calendar c)
    {
        Log.d(TAG, "## startAlarm ##");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, activity_AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if(c.before(Calendar.getInstance()))
        {
            c.add(Calendar.DATE, 1);
        }

        //기기가 절전 모드일 때 절전모드 해제 후 알림
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    //알람 취소
    private  void cancelAlarm()
    {
        Log.d(TAG, "## cancelAlarm ##");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, activity_AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        time_text.setText("재설정");
    }

    public void onHome1(View view)
    {
        Intent intent = new Intent(getApplicationContext(),activity_Main_m.class);
        startActivity(intent);
    }
}
