package com.example.team_jbd;

import static android.app.PendingIntent.getActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class activity_Logo extends AppCompatActivity
{
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo); //xml , java 소스 연결
        mContext = this;
        GoToMainActivity(mContext);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish(); // 앱 종료 시 로고 표시 x
    }

    public void GoToMainActivity(Context mContext)
    {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()
        {
            public void run()
            {
                Intent mIntent = new Intent(mContext, activity_Login.class);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1)
                {
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                else mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mIntent);
            }
        }, 3000); // 3초 후 본화면으로 넘김

    }
}
