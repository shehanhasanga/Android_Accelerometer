package com.example.shehan.app1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_GYROSCOPE;

public class MainActivity extends AppCompatActivity {

    private SensorManager  mSensorManager;
    private Sensor accelmeter;
    private SensorEventListener acellistener;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Button button;
    private boolean active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        active=false;
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv1.setText("X value : ");
        tv2.setText("Y value : ");
        tv3.setText("Z value : ");
        button= findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showtast("clicked");
                // click handling code
            }
        });
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelmeter = mSensorManager.getDefaultSensor(TYPE_ACCELEROMETER);
        if (accelmeter==null){
            Toast.makeText(this,"this device has not gyroscope",Toast.LENGTH_LONG).show();
        }
        acellistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                tv1.setText("X value : "+ event.values[0]);
                tv2.setText("Y value : "+ event.values[1]);
                tv3.setText("Z value : "+ event.values[2]);
//                showtast("shehan");
//                showtast(event.values.toString());
//                Toast.makeText(this,"",Toast.LENGTH_LONG).show();
//                Toast.makeText(this,event.values.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }
    @Override
    protected void onResume(){
        super.onResume();
//        mSensorManager.registerListener(acellistener,accelmeter,SensorManager.SENSOR_DELAY_FASTEST);
}
    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(acellistener);
    }
    private void showtast(String text){
        if(!active){
            mSensorManager.registerListener(acellistener,accelmeter,SensorManager.SENSOR_DELAY_FASTEST);
            Toast.makeText(this,"Accelerometer service is started",Toast.LENGTH_SHORT).show();
            button.setText("STOP");
            active=true;
        }
        else {
            mSensorManager.unregisterListener(acellistener);
            Toast.makeText(this,"Accelerometer service is stopped",Toast.LENGTH_SHORT).show();
            button.setText("START");
            active=false;
        }
//        mSensorManager.registerListener(acellistener,accelmeter,SensorManager.SENSOR_DELAY_FASTEST);
//        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();

    }


//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        tv1.setText("x value : "+ event.values[0]);
//    }
}

