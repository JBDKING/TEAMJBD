package com.example.team_jbd;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class activity_Bluetooth extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permission_list =
                {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                };

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }
}
