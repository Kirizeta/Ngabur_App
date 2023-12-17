package com.example.ngabur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HistoryList())
                    .commit();
        }
    }
}