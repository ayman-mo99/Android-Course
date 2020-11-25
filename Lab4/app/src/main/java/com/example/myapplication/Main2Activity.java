package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    String TAG = "Activity";
    TextView t1;
    TextView t2;
    CheckBox ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
        t1 = findViewById(R.id.t11);
        t2 = findViewById(R.id.t22);
        ph = findViewById(R.id.ph2);
        //Get data
        Intent intent = getIntent();
        String s1 = intent.getStringExtra("ph");
        String s2 = intent.getStringExtra("ms");
        boolean t = intent.getBooleanExtra("po",true);

        t1.setText(s1);
        t2.setText(s2);
        ph.setChecked(t);

    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}
