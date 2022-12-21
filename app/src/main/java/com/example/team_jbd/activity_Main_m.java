package com.example.team_jbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class activity_Main_m extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);
    }

    public void onSetting(View view)
    {
        Intent intent = new Intent(getApplicationContext(),activity_Seting.class);
        startActivity(intent);
    }

    public void onHome(View view)
    {
        Intent intent = new Intent(getApplicationContext(),activity_Main.class);
        startActivity(intent);
    }

    public void onDog_tip(View view)
    {
        Intent intent = new Intent(getApplicationContext(),activity_Tip.class);
        startActivity(intent);
    }
}
