package com.example.ex3;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Calendar;
import android.widget.TextView;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Locale;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button showDatetimeButton;
    private TextView datetimeTextView;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDatetimeButton = findViewById(R.id.show_datetime_button);
        datetimeTextView = findViewById(R.id.datetime_textview);

        showDatetimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUpdatingDateTime();
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                updateDateTime();
                handler.postDelayed(this, 1000);
            }
        };
    }


    private void startUpdatingDateTime() {
        handler.postDelayed(runnable, 0);
    }

    private void updateDateTime() {
        Date currentTime = Calendar.getInstance().getTime();
        String message = "Thời gian hiện hành " ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a", Locale.getDefault());
        String currentDateTime = dateFormat.format(currentTime);

        datetimeTextView.setText(message + currentDateTime);
    }
}

