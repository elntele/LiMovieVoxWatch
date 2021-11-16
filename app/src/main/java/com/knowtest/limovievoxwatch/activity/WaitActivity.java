package com.knowtest.limovievoxwatch.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.knowtest.limovievoxwatch.R;

import java.util.Timer;
import java.util.TimerTask;

public class WaitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        this.getSupportActionBar().hide();
        Timer timer = new Timer();
        MyTimerTask myTask = new MyTimerTask();
        timer.schedule(myTask, 10000, 20000);
    }


    class MyTimerTask extends TimerTask {
        public void run() {
            finish();

        }

    }
}
