package com.example.onlineshop;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SensorsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);

        ArrayList<String> list = new ArrayList<String>();

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ListView sensorList = (ListView) findViewById(R.id.sensor_list);
        ArrayAdapter<String> adapter;

        for(int i = 0; i < deviceSensors.size(); i++) {
            String info = deviceSensors.get(i).toString();
            list.add(info);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        sensorList.setAdapter(adapter);
    }
}


