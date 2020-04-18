package com.example.sixthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager manager,mmanager,amanager;
    EditText editText1;
    EditText editText2;
    EditText editText3, editText4, editText5, editText6, editText7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.edit1);
        editText2 = (EditText) findViewById(R.id.edit2);
        editText3 = (EditText) findViewById(R.id.edit3);
        editText4 = (EditText) findViewById(R.id.edit4);
        editText5 = (EditText) findViewById(R.id.edit5);
        editText6 = (EditText) findViewById(R.id.edit6);
        editText7 = (EditText) findViewById(R.id.edit7);
        manager =(SensorManager) getSystemService(SENSOR_SERVICE);
    }
    protected void onResume(){
        super.onResume();
        manager.registerListener(this,manager.getDefaultSensor
                (Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this,manager.getDefaultSensor
                (Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this,manager.getDefaultSensor
                (Sensor.TYPE_AMBIENT_TEMPERATURE),SensorManager.SENSOR_DELAY_GAME);
    }
    //@Override
    protected void onStop(){
        manager.unregisterListener(this);
        super.onStop();
    }
    protected void onPause(){
        manager.unregisterListener(this);
        super.onPause();
    }
    public void onAccuracyChanged(Sensor sensor,int accuracy){ }
    public void onSensorChanged(SensorEvent event){
        float[] values =event.values;
        int sensorType =event.sensor.getType();
        StringBuilder sa=null;
        StringBuilder sb=null;
        StringBuilder sc=null;
        switch(sensorType){
            case Sensor.TYPE_ORIENTATION:
                sa=new StringBuilder();
                sa.append("绕x轴旋转的角度：");
                sa.append(values[0]);
                editText1.setText(sa.toString());
                sb=new StringBuilder();
                sb.append("绕y轴旋转角度：");
                sb.append(values[1]);
                editText2.setText(sb.toString());
                sc=new StringBuilder();
                sc.append("绕z轴旋转角度:");
                sc.append(values[2]);
                editText3.setText(sc.toString());
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                sa=new StringBuilder();
                sa.append("x轴方向上的磁场强度:");
                sa.append(values[0]);
                editText4.setText(sa.toString());
                sb=new StringBuilder();
                sb.append("y轴方向上的磁场强度:");
                sb.append(values[1]);
                editText5.setText(sb.toString());
                sc=new StringBuilder();
                sc.append("z轴方向上的磁场强度:");
                sc.append(values[2]);
                editText6.setText(sc.toString());
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sa=new StringBuilder();
                sa.append("当前温度为:");
                sa.append(values[0]);
                editText7.setText(sa.toString());
        }
    }
}