package com.example.amisha.wifibluetoothaccelerometer;

import android.bluetooth.BluetoothAdapter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    ImageView i1;
    SensorManager sm;
    Sensor s;
    BluetoothAdapter ba;
    WifiManager wm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i1=(ImageView)findViewById(R.id.imageView);
        wm=(WifiManager)getSystemService(WIFI_SERVICE);
        ba=BluetoothAdapter.getDefaultAdapter();
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        int x1=(int)x;
        int y1=(int)y;
        int z1=(int)z;
        if (x1!=0)
        {
            wm.setWifiEnabled(true);
            ba.enable();
            i1.setImageResource(R.drawable.a);
        }
        else
        {
            wm.setWifiEnabled(false);
            ba.disable();
            i1.setImageResource(R.drawable.b);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
