package com.example.poolbismapping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddTerminalActivity extends AppCompatActivity {
    
    private EditText editTextNama, editTextAlamat, editTextKecamatan, editTextLat, editTextLong,
                     editTextAkap, editTextDalkot, editTextJabo, editTextDesk, editTextTrayek;
    
    private ImageView imageViewTerminal;
    
    private DatabaseReference terminalRef;
    private StorageReference imageReference;
    private Uri imageUri;
    
    private String nama, alamat, kecamatan, lat, longi, akap, dalkot, jabo, deskripsi, trayek;
    private String downloadImageUrl, saveCurrentTime, saveCurrentDate;
    private String randomKey;
    
    private static final int galleryPick = 1;
    
    private ProgressDialog progressDialog;
    
    private Toolbar toolbar;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terminal);

        imageViewTerminal = findViewById(R.id.img_add_terminal);

        editTextNama = findViewById(R.id.txt_add_nama_terminal);
        editTextAlamat = findViewById(R.id.txt_add_alamat_terminal);
        editTextKecamatan = findViewById(R.id.txt_add_kecamatan_terminal);
        editTextLat = findViewById(R.id.txt_add_lat_terminal);
        editTextLong = findViewById(R.id.txt_add_long_terminal);
        editTextAkap = findViewById(R.id.txt_add_akap_terminal);
        editTextDalkot = findViewById(R.id.txt_add_dalkot_terminal);
        editTextJabo = findViewById(R.id.txt_add_jabodetabek_terminal);
        editTextDesk = findViewById(R.id.txt_add_deskripsi);
        editTextTrayek = findViewById(R.id.txt_add_trayek);
        

        
        toolbar = findViewById(R.id.toolbar_add_terminal);
        setSupportActionBar(toolbar);
        
        terminalRef = FirebaseDatabase.getInstance().getReference().child("Terminal");
        imageReference = FirebaseStorage.getInstance().getReference().child("Terminal Images");
        
        imageViewTerminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                ambilGambar();
            }
        });

        progressDialog = new ProgressDialog(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_add_pool, menu);
        
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        if (item.getItemId() == R.id.save_menu){
            
            simpanSemuaData();
        }
        
        return super.onOptionsItemSelected(item);
    }

    private void ambilGambar() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT).setType("image/*");
        startActivityForResult(galleryIntent, galleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == galleryPick && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            imageViewTerminal.setImageURI(imageUri);
        } else {

            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddTerminalActivity.this, AddTerminalActivity.class));
            finish();
        }
    }



    private void simpanSemuaData() {

        nama = editTextNama.getText().toString();
        alamat = editTextAlamat.getText().toString();
        kecamatan = editTextKecamatan.getText().toString();
        lat = editTextLat.getText().toString();
        longi = editTextLong.getText().toString();
        akap = editTextAkap.getText().toString();
        dalkot = editTextDalkot.getText().toString();
        jabo = editTextJabo.getText().toString();
        deskripsi = editTextDesk.getText().toString();
        trayek = editTextTrayek.getText().toString();

        if (imageUri == null){

            Toast.makeText(this, "Gambar tidak boleh kosong", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(nama)) {

            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(alamat)) {

            Toast.makeText(this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(kecamatan)) {

            Toast.makeText(this, "Kecamatan tidak boleh kosong", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(lat)) {

            Toast.makeText(this, "Latitude tidak boleh kosong", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(longi)) {

            Toast.makeText(this, "Longitude tidak boleh kosong", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(deskripsi)) {

            Toast.makeText(this, "Deskripsi tidak boleh kosong", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(trayek)) {

            Toast.makeText(this, "Trayek tidak boleh kosong", Toast.LENGTH_SHORT).show();

        } else {

            simpanKeDatabase();
        }
    }

    private void simpanKeDatabase() {

        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        randomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = imageReference.child(imageUri.getLastPathSegment() + randomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(AddTerminalActivity.this, "Gagal menyimpan gambar" + message, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(AddTerminalActivity.this, "Gambar berhasil diupload", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()){

                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();

                        return filePath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if (task.isSuccessful()){

                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AddTerminalActivity.this, "Url Gambar berhasil didownload", Toast.LENGTH_SHORT).show();

                            dataYangAkanDisimpan();
                        }
                    }
                });

            }
        });

    }

    private void dataYangAkanDisimpan() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("image",downloadImageUrl);
        hashMap.put("tid", randomKey);
        hashMap.put("nama", nama);
        hashMap.put("alamat", alamat);
        hashMap.put("kecamatan", kecamatan);
        hashMap.put("lat", lat);
        hashMap.put("longi", longi);
        hashMap.put("akap", akap);
        hashMap.put("dalkot", dalkot);
        hashMap.put("jabo", jabo);
        hashMap.put("deskripsi", deskripsi);
        hashMap.put("trayek", trayek);

        terminalRef.child(randomKey).updateChildren(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    progressDialog.dismiss();
                    Toast.makeText(AddTerminalActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();


                } else {

                    progressDialog.dismiss();
                    String message = task.getException().toString();
                    Toast.makeText(AddTerminalActivity.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
