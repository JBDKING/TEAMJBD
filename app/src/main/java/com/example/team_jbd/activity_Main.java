package com.example.team_jbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_Main extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSeting(View view)
    {
        Intent intent = new Intent(getApplicationContext(),activity_Seting.class);
        startActivity(intent);
    }


}