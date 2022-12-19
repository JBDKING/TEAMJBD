package com.example.team_jbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class activity_Main extends AppCompatActivity
{

    Context mContext;
    @BindView(R.id.main_text)
    TextView main_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext=this;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(activity_Data.getInstance(mContext).getName());

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                // 초기값으로 호출 후 재호출
                // 데이터가 업데이트 될 때마다
                String value = dataSnapshot.getValue(String.class);
                main_text.setText(value);
                Log.d("JBD", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // 값을 불러올 수 없을 때
                Log.w("JBD", "값을 가져올 수 없어요", error.toException());
            }
        });

    }

    public void onNext(View view)
    {
        Intent intent = new Intent(getApplicationContext(),activity_Main_m.class);
        startActivity(intent);
    }
}