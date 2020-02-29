package com.example.fishermanproperties;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class NavigationTest extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewFlipper v_flipper;
    NavigationView navigationView;

    public AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        int[] images = {R.drawable.house2, R.drawable.house1, R.drawable.pexels1, R.drawable.pexels2, R.drawable.pexels3};

        v_flipper = findViewById(R.id.v_flipper);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        for (int image : images) {
            flipperImages(image);
        }
    }


    public void buttongallery(View v) {
        Intent intent = new Intent(this, Gallery.class);
        startActivity(intent);
    }

    public void buttonlocations(View v) {

        Intent intent = new Intent(this, Locatn.class);
        startActivity(intent);
    }

    public void buttontestimonials(View V) {

        Intent intent = new Intent(this, Testimonials.class);
        startActivity(intent);
    }

    public void buttonContactus(View v) {

        Intent intent = new Intent(this, ContactUs.class);
        startActivity(intent);
    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000); //4sec
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.viewgallery:
            case R.id.testimonials:
                startActivity(new Intent(NavigationTest.this, Gallery.class));
                break;
            case R.id.ourlocations:
                startActivity( new Intent(NavigationTest.this, Locatn.class));
                break;
            case R.id.contactus:
            case R.id.nav_share:
            case R.id.nav_send:
                startActivity(new Intent(NavigationTest.this, ContactUs.class));
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }




}

