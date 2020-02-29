package com.example.fishermanproperties;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class Gallery extends AppCompatActivity implements View.OnClickListener{

    TextView chat,chat2, chat3, chat4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chat = findViewById(R.id.chat_now);
        chat2 = findViewById(R.id.chat_now_2);
        chat3 = findViewById(R.id.chat_now_3);
        chat4 = findViewById(R.id.chat_now_4);

        chat.setOnClickListener(this);
        chat2.setOnClickListener(this);
        chat3.setOnClickListener(this);
        chat4.setOnClickListener(this);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chat_now:
                Intent intent = new Intent(this, chat_activity.class);
                String user_name = "Four bedroom bungalow for sale at Gbagada, well " +
                        "furnished, all you need do is just move in, all " +
                        "appliances required has been installed already...Enjoy!! "+
                        "When will you be available for the inspection........  " +
                        "please provide us with your schedule";
                intent.putExtra("property", user_name);
                startActivity(intent);
                break;
            case R.id.chat_now_2:
                Intent inten = new Intent(this, chat_activity.class);
                String proper = "1 plot of land at magodo for sale with its full papers " +
                        "complete. it is a dry land very ready for  " +
                        "construction at a cheap price up for grabs!!! "+
                        "When will you be available for the inspection..........  " +
                        "please provide us with your schedule";
                inten.putExtra("property", proper);
                startActivity(inten);
                break;
            case R.id.chat_now_3:
                Intent inte = new Intent(this, chat_activity.class);
                String prope = "Half plot of land for sale at Ikorodu at a " +
                        "cheap rate ready for construction, half completed " +
                        "foundation..This is a dry land "+"When will you be available for the inspection.............." +
                        "please provide us with your schedule";
                inte.putExtra("property", prope);
                startActivity(inte);
                break;
            case R.id.chat_now_4:
                Intent in = new Intent(this, chat_activity.class);
                String prop = "Duplex for sale at Egbeda, structure made already with " +
                        "completion to be made by buyer..." +
                        "The area is very residential and conducive "+"When will you be available for the inspection..........." +
                        "please provide us with your schedule";
                in.putExtra("property", prop);
                startActivity(in);
                break;
        }
    }



}
