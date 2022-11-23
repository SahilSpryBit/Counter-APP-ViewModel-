package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView countnum;
    Button startbtn, stopbtn;

    MainActivityViewModel activityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        countnum = findViewById(R.id.countnum);
        startbtn = findViewById(R.id.startbtn);
        stopbtn =findViewById(R.id.stopbtn);

        getLifecycle().addObserver(activityViewModel);

        activityViewModel.getCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                countnum.setText("Counter : "+ integer);
            }
        });

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activityViewModel.startMethod();

            }
        });

        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activityViewModel.stopMethod();

            }
        });
    }
}