package com.example.fishermanproperties;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Locatn extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatn);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker in Sydney and move the camera
        LatLng Fishermanproperties1 = new LatLng(6.427245, 3.415765);
        googleMap.addMarker(new MarkerOptions().position(Fishermanproperties1).title("Fisherman Properties"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Fishermanproperties1));

        LatLng Fishermanproperties2 = new LatLng(6.589901, 3.283995);
        googleMap.addMarker(new MarkerOptions().position(Fishermanproperties2).title("Fisherman Properties"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Fishermanproperties2));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Fishermanproperties1, 12.0f));
    }
}
