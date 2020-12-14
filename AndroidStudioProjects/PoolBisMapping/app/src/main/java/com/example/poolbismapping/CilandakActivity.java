package com.example.poolbismapping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CilandakActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap apiMap;
    private SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cilandak);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        apiMap = googleMap;

        LatLng cilandak = new LatLng(-6.290800, 106.818386);

        apiMap.addMarker(new MarkerOptions().position(cilandak).title("Cilandak"));
//        apiMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cilandak, 16f));
        apiMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cilandak, 16.0f));

    }
}
