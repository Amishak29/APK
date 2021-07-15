package com.example.amisha.accelerometersensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ImageView i1;
    MediaPlayer mp;
    SensorManager sm;
    Sensor s;
    //TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i1=(ImageView)findViewById(R.id.imageView);
        mp=MediaPlayer.create(this,R.raw.s);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        String s1=Float.toString(x);
        //t1.setText(s1);
        //String s2=Float.toString(y);
        //t2.setText(s2);
        //String s3=Float.toString(z);
        //t3.setText(s3);
        int x1=(int)x;
        int y1=(int)y;
        int z1=(int)z;
        if (x1!=0)
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
