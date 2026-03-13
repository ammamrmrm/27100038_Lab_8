package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Retrieve city name from Intent
        String cityName = getIntent().getStringExtra("CITY_NAME");
        TextView display = findViewById(R.id.textView_city_display);
        display.setText(cityName);

        // Back button finishes the activity to return to MainActivity
        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> finish());
    }
}