package com.example.laborator2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class SensorsActivity extends AppCompatActivity implements SensorEventListener {
    @Override
    public void onSensorChanged(SensorEvent event) {

        sensorValues.put(event.sensor.getName(),String.valueOf(event.values[0]));
        this.manager.unregisterListener(this,event.sensor);
        String values=new String();
        for (float value:event.values)
        {
            values+=value+" ";

        }
        sensorInformation.setText(sensorInformation.getText()+"\n"+event.sensor.getName()+" "+values+"\n");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private TextView sensorInformation;
    private SensorManager manager;
    private List<Sensor> sensors;
    private HashMap<String,String> sensorValues=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        sensorInformation=(TextView)findViewById(R.id.sensorInformation);
        sensorInformation.setMovementMethod(new ScrollingMovementMethod());
        manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensors=manager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor:sensors)
        {
            manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

}
