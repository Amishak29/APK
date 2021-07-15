package com.example.amisha.torchaccelerometer1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    ImageView i1;
    TextView t1,t2,t3;
    CameraManager cm;
    SensorManager sm;
    Sensor s;
    private boolean flash=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i1=(ImageView) findViewById(R.id.imageView);
        t1=(TextView)findViewById(R.id.editText);
        t2=(TextView)findViewById(R.id.editText2);
        t3=(TextView)findViewById(R.id.editText3);
        cm=(CameraManager)getSystemService(CAMERA_SERVICE);
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
        String s2=Float.toString(y);
        String s3=Float.toString(z);
        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);

        int x1=(int)x;
        int y1=(int)y;
        int z1=(int)z;
        if (x1!=0)
        {
            try{
                String s = cm.getCameraIdList()[0];
                cm.setTorchMode(s,true);
            }
            catch(CameraAccessException e){

            }
            i1.setImageResource(R.drawable.a);

        }
        else
        {
            try{
                String s = cm.getCameraIdList()[0];
                cm.setTorchMode(s,false);
            }
            catch(CameraAccessException e){

            }
            i1.setImageResource(R.drawable.b);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
