package com.example.amisha.lightsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    MediaPlayer mp;
    SensorManager sm;
    Sensor s;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i1=(ImageView)findViewById(R.id.imageView);
        mp=(MediaPlayer.create(this,R.raw.s));
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0]>1)
        {
            mp.start();
            i1.setImageResource(R.drawable.off);
        }
        else
        {
            mp.pause();
            i1.setImageResource(R.drawable.on);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
