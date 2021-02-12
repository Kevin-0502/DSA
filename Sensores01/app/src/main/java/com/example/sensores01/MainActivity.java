package com.example.sensores01;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  implements SensorEventListener{

    ContainerView containerView;
    SensorManager sensorManager;
    Sensor sensorAccelerometer,sensorLight;

    Vibrator v;
    int vibrationTime=25;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerView=(ContainerView) findViewById(R.id.container);

        sensorManager=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);

        sensorAccelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensorAccelerometer,SensorManager.SENSOR_DELAY_GAME);

        v=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor= event.sensor;
        switch (sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                float x=event.values[0];
                float y=event.values[1];

                if (containerView.containerWidth>=0 && containerView.containerWidth<=containerView.getWidth()){
                    containerView.containerWidth+=-x*2;
                    if (containerView.containerWidth<0){
                        containerView.containerWidth=0;
                        v.vibrate(vibrationTime);
                    }

                }
                if (containerView.containerHeight >= 0 && containerView.containerHeight <= containerView.getHeight()){
                    containerView.containerHeight+=y*2;
                    if (containerView.containerHeight<0){
                        containerView.containerHeight=0;
                        v.vibrate(vibrationTime);
                    }
                }
                if (containerView.containerHeight > containerView.getHeight()){
                    v.vibrate(vibrationTime);

                    containerView.containerHeight=containerView.getHeight();
                }
            break;


            case Sensor.TYPE_LIGHT:
                float light = event.values[0]/500.0f;
                int color;

                if (light<1){
                    color=0xffffffff;
                }
                else
                {
                    float red,green,blue;
                    red=green=blue=225*light;

                    color= Color.rgb(Math.round(red),Math.round(green),Math.round(blue));
                }
                containerView.color=color;
                break;

        }//fin switch

        containerView.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    protected void onResume(){

        super.onResume();
        sensorManager.registerListener(this,sensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,sensorLight,SensorManager.SENSOR_DELAY_NORMAL);
    }

}