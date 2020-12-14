package com.example.poolbismapping.Add;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.poolbismapping.R;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddPoolActivity extends AppCompatActivity {

    //Connect firebase
    private DatabaseReference poolRev;
    private StorageReference imageRev;

    private ImageView imgAdd;
    private TextView txtNamaKecamatan;
    private EditText editTextAddNama, editTextAddNoTelp, editTextAddHargaTiket,
                    editTextAddLat, editTextAddLong, editTextAddAlamat, editTextAddTujuan;

    private Toolbar toolbar;

    //tempat menyimpan data yang di input
    private String nama, notelp, hargatiket, latitude, longitude, alamat, tujuan;

    //tempat nyimpen link gambar yang di upload ke storage firebase
    //dan mneyimpan data id dari current date untuk di taro di firebase
    private String downloadedImageURL, randomKey;

    //untuk menyimpan sementara data hari dan waktu untuk id di dlm database
    private String saveCurrentDate, saveCurrentTime;

    //untuk mengambil gambar dari media
    private static final int galleryPick = 1;
    private Uri imageUri;

    //loading dialog
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pool);

        //init UI
        imgAdd = findViewById(R.id.img_add_pool);
        editTextAddNama = findViewById(R.id.txt_add_nama_pool);
        editTextAddNoTelp = findViewById(R.id.txt_add_notelp);
        editTextAddHargaTiket = findViewById(R.id.txt_add_harga_tiket);
        editTextAddLat = findViewById(R.id.txt_add_lat);
        editTextAddLong = findViewById(R.id.txt_add_long);
        editTextAddAlamat = findViewById(R.id.txt_add_alamat);
        editTextAddTujuan = findViewById(R.id.txt_add_tujuan);
        txtNamaKecamatan = findViewById(R.id.txt_nama_kecamatan);

        //toolbar
        toolbar = findViewById(R.id.toolbar_add_pool);
        setSupportActionBar(toolbar);

//        kecamatan = editTextKecamatan.getText().toString();

        //intent
        Intent intent = getIntent();
        String kecamatan = intent.getStringExtra("kecamatan");


        txtNamaKecamatan.setText(kecamatan);
        
        //init Database
        poolRev = FirebaseDatabase.getInstance().getReference().child(kecamatan);
        imageRev = FirebaseStorage.getInstance().getReference().child("Pools Image");
        
        //image onclick
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bukaGallrey();
            }

        });

        //loading dialog
        progressDialog = new ProgressDialog(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_add_pool, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save_menu){
            cekField();
        }

        return super.onOptionsItemSelected(item);
    }

    private void bukaGallrey() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, galleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //jika req code true dan hasil ok dan data benar dan tidak kosong
        if (requestCode == galleryPick && resultCode == RESULT_OK && data !=null) {

            //maka ambil data yg dipilih dari galeri dan dimasukkan
            //ke variabel image uri yg memiliki tipe data URI
            imageUri = data.getData();

            // lalu pasang gambar pada image view
            imgAdd.setImageURI(imageUri);
        } else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddPoolActivity.this, AddPoolActivity.class));
            finish();
        }

    }

    //selanjutnya memastikan semua field telah diisi

    private void cekField(){

        nama = editTextAddNama.getText().toString();
        notelp = editTextAddNoTelp.getText().toString();
        hargatiket = editTextAddHargaTiket.getText().toString();
        latitude = editTextAddLat.getText().toString();
        longitude = editTextAddLong.getText().toString();
        alamat = editTextAddAlamat.getText().toString();
        tujuan = editTextAddTujuan.getText().toString();

        if (imageUri == null) {
            Toast.makeText(this, "Gambar belum dipilih!", Toast.LENGTH_SHORT).show();
        }
        else if (nama.isEmpty()){
            Toast.makeText(this, "Nama PO tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }
        else if (notelp.isEmpty()){
            Toast.makeText(this, "Nomer Telfon tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }
        else if (hargatiket.isEmpty()){
            Toast.makeText(this, "Harga tidak boleh kososng", Toast.LENGTH_SHORT).show();
        }
        else if (latitude.isEmpty()){
            Toast.makeText(this, "Latitude harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else if (longitude.isEmpty()){
            Toast.makeText(this, "Longitude harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(alamat)){
            Toast.makeText(this, "Alamat tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(tujuan)){
            Toast.makeText(this, "Tujuan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }
        else {
            simpanSemuaData();
        }

    }

    private void simpanSemuaData() {

        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        randomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = imageRev.child(imageUri.getLastPathSegment() + randomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(AddPoolActivity.this, "Gagal menyimpan data" + message, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddPoolActivity.this, "Gambar sukses diupload", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }

                        downloadedImageURL = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if (task.isSuccessful()){

                            downloadedImageURL = task.getResult().toString();

                            Toast.makeText(AddPoolActivity.this, "Url gambar berhasil disimpan", Toast.LENGTH_SHORT).show();

                            simpanDatakeDatabase();
                        }

                    }
                });
            }
        });

    }


    private void simpanDatakeDatabase(){

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pid", randomKey);
        hashMap.put("image", downloadedImageURL);
        hashMap.put("nama", nama);
        hashMap.put("notelp", notelp);
        hashMap.put("harga", hargatiket);
        hashMap.put("lat", latitude);
        hashMap.put("longi", longitude);
        hashMap.put("alamat", alamat);
        hashMap.put("tujuan", tujuan);
        hashMap.put("kecamatan", txtNamaKecamatan.getText().toString());

        poolRev.child(randomKey).updateChildren(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            progressDialog.dismiss();
                            Toast.makeText(AddPoolActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            clearField();
                        } else {

                            progressDialog.dismiss();
                            String errorMessage = task.getException().toString();
                            Toast.makeText(AddPoolActivity.this, "Gagal menyimpan data" + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void clearField(){

        imgAdd.setImageURI(null);
        editTextAddNama.getText().clear();
        editTextAddNoTelp.getText().clear();
        editTextAddHargaTiket.getText().clear();
        editTextAddLat.getText().clear();
        editTextAddLong.getText().clear();
        editTextAddAlamat.getText().clear();
        editTextAddTujuan.getText().clear();
    }

}
