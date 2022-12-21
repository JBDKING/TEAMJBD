package com.example.team_jbd;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class activity_Bluetooth extends AppCompatActivity
{

    private BluetoothSPP bt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);

        bt = new BluetoothSPP(this); //초기화

        if (!bt.isBluetoothAvailable())
        { //블루투스 사용 불가
            Toast.makeText(getApplicationContext(), "블루투스를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        // ------------------------------ 데이터 수신부 ----------------------------- //
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener()
        { //데이터 수신
            TextView M = findViewById(R.id.textView_M);
            TextView Val = findViewById(R.id.textView_val);

            public void onDataReceived(byte[] data, String message)
            {
                M.setText("물의 양 : " + message + "Kg");
                Val.setText("기울기 :" + message + "");
            }
        });

        // ------------------------------ 데이터 수신부 ----------------------------- //
        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener()
        { //연결됐을 때
            public void onDeviceConnected(String name, String address)
            {
                Toast.makeText(getApplicationContext(), "연결" + name + "\n" + address, Toast.LENGTH_SHORT).show();
            }
            public void onDeviceDisconnected()
            { //연결해제
                Toast.makeText(getApplicationContext()
                        , "연결 해제", Toast.LENGTH_SHORT).show();
            }
            public void onDeviceConnectionFailed()
            { //연결실패
                Toast.makeText(getApplicationContext()
                        , "연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onDestroy()
    {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                bt.connect(data);
            }
        }
        else if (requestCode == BluetoothState.REQUEST_ENABLE_BT)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "블루투스 사용 불가", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
