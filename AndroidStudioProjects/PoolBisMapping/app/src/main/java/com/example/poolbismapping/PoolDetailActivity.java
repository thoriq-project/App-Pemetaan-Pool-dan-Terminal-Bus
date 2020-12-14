package com.example.poolbismapping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poolbismapping.Model.PoolModel;
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

public class PoolDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView textViewNama, textViewAlamat, textViewKecamatan, textViewNotelp, textViewHarga, textViewTujuan;
    private DatabaseReference pooldetailRef;

    private GoogleMap apiMap;
    private SupportMapFragment supportMapFragment;

    private String latitude = "";
    private String longitude = "";
    private String namapool = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_detail);

        textViewNama = findViewById(R.id.txt_nama_pool);
        textViewAlamat = findViewById(R.id.txt_alamat_pool);
        textViewKecamatan = findViewById(R.id.txt_kecamatan_pool);
        textViewNotelp = findViewById(R.id.txt_notelp_pool);
        textViewHarga = findViewById(R.id.txt_harga_pool);
        textViewTujuan = findViewById(R.id.txt_tujuan_pool);

        String pid = getIntent().getStringExtra("pid");
        String kecamatan = getIntent().getStringExtra("kecamatan");

        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        namapool = getIntent().getStringExtra("nama");

        getPoolData(pid, kecamatan);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.pool_fragment);
        supportMapFragment.getMapAsync(this);
    }

    private void getPoolData(final String pid, final String kecamatan) {

        pooldetailRef = FirebaseDatabase.getInstance().getReference().child(kecamatan);
        pooldetailRef.child(pid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    final PoolModel model = dataSnapshot.getValue(PoolModel.class);

                    textViewNama.setText(model.getNama());
                    textViewAlamat.setText(model.getAlamat());
                    textViewKecamatan.setText(model.getKecamatan());
                    textViewNotelp.setText(model.getNotelp());
                    textViewHarga.setText(model.getHarga());
                    textViewTujuan.setText(model.getTujuan());

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
        Double longi = Double.parseDouble(longitude);

        LatLng location = new LatLng(lat,longi);

        apiMap.addMarker(new MarkerOptions().position(location).title(namapool));
        apiMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f));
    }
}
