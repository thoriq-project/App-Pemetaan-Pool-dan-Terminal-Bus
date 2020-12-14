package com.example.poolbismapping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.poolbismapping.Model.Terminal;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class TerminalDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Toolbar toolbar;

    private TextView txtNama, txtAlamat, txtKecamatan, txtAkap, txtDalkot, txtJabo, txtDesk, txtTrayek;

    private GoogleMap apiMap;
    private SupportMapFragment supportMapFragment;

    private DatabaseReference terminalRef;

    private String tid = "";
    private String latitude = "";
    private String longi = "";
    private String nama = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal_detail);

        txtNama = findViewById(R.id.txt_terminal_detail_nama);
        txtAlamat = findViewById(R.id.txt_terminal_detail_alamat);
        txtKecamatan = findViewById(R.id.txt_terminal_detail_kec);
        txtAkap = findViewById(R.id.txt_terminal_detail_akap);
        txtDalkot = findViewById(R.id.txt_terminal_detail_dalkot);
        txtJabo = findViewById(R.id.txt_terminal_detail_jabo);
        txtDesk = findViewById(R.id.txt_terminal_detail_desk);
        txtTrayek = findViewById(R.id.txt_terminal_detail_trayek);

        toolbar = findViewById(R.id.terminal_detail_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        tid = getIntent().getStringExtra("tid");
        latitude = getIntent().getStringExtra("lat");
        longi = getIntent().getStringExtra("longi");
        nama = getIntent().getStringExtra("nama");

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.terminal_fragment_maps);
        supportMapFragment.getMapAsync(this);

        getDataTerminal(tid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return super.onSupportNavigateUp();
    }

    private void getDataTerminal(String tid) {

        terminalRef = FirebaseDatabase.getInstance().getReference("Terminal");
        terminalRef.child(tid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    final Terminal terminal = dataSnapshot.getValue(Terminal.class);

                    if (terminal.getAkap().isEmpty()){

                        txtAkap.setVisibility(View.GONE);

                    } else if (terminal.getDalkot().isEmpty()){

                        txtDalkot.setVisibility(View.GONE);
                    } else if (terminal.getJabo().isEmpty()){

                        txtJabo.setVisibility(View.GONE);
                    }

                    txtNama.setText(terminal.getNama());
                    txtAlamat.setText(terminal.getAlamat());
                    txtKecamatan.setText(terminal.getKecamatan());
                    txtAkap.setText(terminal.getAkap());
                    txtDalkot.setText(terminal.getDalkot());
                    txtJabo.setText(terminal.getJabo());
                    txtDesk.setText(terminal.getDeskripsi());
                    txtTrayek.setText(terminal.getTrayek());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        apiMap = googleMap;

        Double lat = Double.parseDouble(latitude);
        Double longit = Double.parseDouble(longi);

        LatLng latLng = new LatLng(lat, longit);

        apiMap.addMarker(new MarkerOptions().position(latLng).title(nama));
        apiMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
