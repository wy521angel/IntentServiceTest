package com.example.intentservicetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class IntentServiceActivity extends AppCompatActivity {

    private static String TAG = "IntentServiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent(IntentServiceActivity.this, LocalIntentService.class);
                service.putExtra("task_action", "com.example.wy521angel.threadtest.TASK1");
                startService(service);
                service.putExtra("task_action", "com.example.wy521angel.threadtest.TASK2");
                startService(service);
                service.putExtra("task_action", "com.example.wy521angel.threadtest.TASK3");
                startService(service);
            }
        });
    }
}