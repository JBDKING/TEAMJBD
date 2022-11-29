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
    Spinner spinner1, spinner2;
    TextView textView1, textView2;
    DatabaseReference databaseReference;
    String item1, item2;
    activity_Member member;

    Context mContext;

    String[] pet = {"선택","웰시코기","시바견","말티즈","푸들","포메라니안","비숑"};
    String[] wei = {"선택","1","2","3","4","5","6"};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        mContext = this;

        textView1 = findViewById(R.id.dog);
        textView2 = findViewById(R.id.wei);

        button = findViewById(R.id.button_save);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Value");

        spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);

        member = new activity_Member();

        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,pet);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,wei);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(arrayAdapter1);
        spinner2.setAdapter(arrayAdapter2);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveValue(item1);
                saveValue(item2);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        item1 = spinner1.getSelectedItem().toString();
        textView1.setText(item1);

        item2 = spinner2.getSelectedItem().toString();
        textView2.setText(item2);
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