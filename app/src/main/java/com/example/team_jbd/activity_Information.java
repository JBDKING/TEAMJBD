package com.example.team_jbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_Information extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    Button button;
    Spinner spinner;
    DatabaseReference databaseReference;
    String item;
    activity_Member member;

    Context mContext;

    String[] pet = {"선택","웰시코기","시바견","말티즈","푸들","포메라니안","비숑"};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        mContext = this;

        button = findViewById(R.id.button_save);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Value");
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        member = new activity_Member();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,pet);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                saveValue(item);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        item = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {
    }

    void saveValue(String item)
    {
        if (item == "선택")
        {
            Toast.makeText(this,"견종을 선택 하세요!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            member.setPet(item);
            String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(member);

            Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
        }
    }
}