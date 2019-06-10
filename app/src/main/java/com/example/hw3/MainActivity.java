package com.example.hw3;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    boolean roll = false;
    private String [] Answer = new String[20];
    ImageView ball;
    TextView text;
    Animation shake;
    Date start;
    Date stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Answer[0] = "It is certain.";
        Answer[1] = "It is decidedly so";
        Answer[2] = "Without a doubt.";
        Answer[3] = "Yes - definitely.";
        Answer[4] = "You may rely on it.";
        Answer[5] = "As I see it, yes.";
        Answer[6] = "Most likely.";
        Answer[7] = "Outlook good.";
        Answer[8] = "Yes.";
        Answer[9] = "Signs point to yes.";
        Answer[10] = "Reply hazy, try again.";
        Answer[11] = "Ask again later.";
        Answer[12] = "Better not tell you now.";
        Answer[13] = "Cannot predict now.";
        Answer[14] = "Concentrate and ask again.";
        Answer[15] = "Don't count on it.";
        Answer[16] = "My reply is no.";
        Answer[17] = "My sources say no.";
        Answer[18] = "Outlook not so good.";
        Answer[19] = "Very doubtful.";

        ball = findViewById(R.id.imageView);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        text = findViewById(R.id.textView);
        SensorManager lightSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor lightSensor = lightSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightSensorManager.registerListener(
                lightSensorListener,
                lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private final SensorEventListener lightSensorListener
            = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                if (event.values[0] < 100.0 && !roll) {
                    text.setTextSize(64);
                    text.setText("8");
                    start = Calendar.getInstance().getTime();
                    roll = true;
                } else if (event.values[0] > 100.0 && roll) {
                    stop = Calendar.getInstance().getTime();
                    int result = (int) ((stop.getTime() - start.getTime()) % 20);
                    roll = false;
                    text.setTextSize(16);
                    text.setText(Answer[result]);

                } if (roll) {
                    ball.startAnimation(shake);
                }
            }
        }

    };
}
