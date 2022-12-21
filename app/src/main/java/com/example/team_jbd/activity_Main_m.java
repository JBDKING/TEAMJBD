package com.example.team_jbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_Main_m extends AppCompatActivity
{
    private TextView text_M,text_val;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    //DatabaseReference conditionRef = mRootRef;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);

        text_M = (TextView) findViewById(R.id.textView_M);
        text_val = (TextView) findViewById(R.id.textView_val);

        try
        {
            mRootRef.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    String text1 = dataSnapshot.child("W").getValue().toString();
                    text_M.setText(text1);

                    String text2 = dataSnapshot.child("S").getValue().toString();
                    text_val.setText(text2);
                }

                @Override
                public void onCancelled(DatabaseError databaseError)
                {

                }
            });
        }
        catch(Exception e)
        {

        }
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
