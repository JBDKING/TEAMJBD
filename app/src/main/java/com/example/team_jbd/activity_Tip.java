package com.example.team_jbd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class activity_Tip extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
