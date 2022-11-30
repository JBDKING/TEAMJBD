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

    String[] pet = {"견종","골든리트리버","닥스훈트","말티즈","믹스견","비글","비숑 프리제","사모예드","스피츠","시바견","시츄",
                    "요크셔테리어","웰시코기","진돗개","치와와","포메라니안","푸들","허스키"};
    String[] wei = {"몸무게","0~5kg","5~10kg","10~15kg","15~20kg","20~30kg","30kg↑"};

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
        if (item == "견종")
        {
            Toast.makeText(this,"견종을 선택 하세요!",Toast.LENGTH_SHORT).show();
        }
        else if (item == "몸무게")
        {
            Toast.makeText(this,"몸무게를 선택 하세요!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            member.setPet(item);
            String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(member);

            Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(activity_Information.this, activity_Main.class);
            startActivity(intent);
            finish();
        }
    }
}