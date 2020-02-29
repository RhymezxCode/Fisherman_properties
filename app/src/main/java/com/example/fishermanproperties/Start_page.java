package com.example.fishermanproperties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class Start_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);


        Thread xyz = new Thread() {
            public void run() {
                try {
                    sleep(3300);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(Start_page.this, Home.class);
                    startActivity(intent);
                }
            }
        };
        xyz.start();
    }


}
