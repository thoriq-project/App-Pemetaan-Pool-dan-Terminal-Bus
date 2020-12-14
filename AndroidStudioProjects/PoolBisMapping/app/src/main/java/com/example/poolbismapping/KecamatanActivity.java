package com.example.poolbismapping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class KecamatanActivity extends AppCompatActivity {

    private CardView cvCilandak, cvJagakarsa, cvKebBaru, cvKebLama, cvMampang, cvPancoran, cvPsMinggu,
    cvPesanggrahan, cvSetiaBudi, cvTebet;

    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kecamatan);

        initImageView();
        initCardView();


    }

    private void initImageView() {

        img1 = findViewById(R.id.img_cilandak);
        img2 = findViewById(R.id.img_jagakarsa);
        img3 = findViewById(R.id.img_mampang);
        img4 = findViewById(R.id.img_keblama);
        img5 = findViewById(R.id.img_kebbaru);
        img6 = findViewById(R.id.img_pancoran);
        img7 = findViewById(R.id.img_psminggu);
        img8 = findViewById(R.id.img_pesanggrahan);
        img9 = findViewById(R.id.img_setiabudi);
        img10 = findViewById(R.id.img_tebet);

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fcilandak.jpg?alt=media&token=a1812400-02a1-4e9d-bf1f-113899a6c947").into(img1);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fjagakarsa.jpg?alt=media&token=449a730c-16b9-4065-bdb5-1cb171b62877").into(img2);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fmampang.jpeg?alt=media&token=ac5b3fc2-e317-4d09-9daf-74bd55b99480").into(img3);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fkeblama.jpg?alt=media&token=abab8814-e520-4894-9702-3cdb70300ac4").into(img4);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fkebbaru.png?alt=media&token=b35cc233-b4d1-4139-9f9a-fb3da61aeab5").into(img5);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fpancoran.jpg?alt=media&token=404e89f5-30f3-4dbc-b422-fc149ac1640d").into(img6);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fpasarminggu.jpg?alt=media&token=691f2921-0885-4743-a161-1b66d3aa7204").into(img7);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fpesanggrahan.jpg?alt=media&token=dfe17418-3c6a-4737-b29b-a4bc8cd18688").into(img8);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Fsetiabudi.jpeg?alt=media&token=bbeea68a-5e37-4c16-8eed-30bbfdd569ee").into(img9);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/poolbismapping.appspot.com/o/image%20card%20view%2Ftebet.jpg?alt=media&token=9df41402-3e98-4c5c-8216-8af2766f9d22").into(img10);
    }

    private void initCardView() {

        cvCilandak = findViewById(R.id.card_pool_cilandak);
        cvJagakarsa = findViewById(R.id.card_pool_jagakarsa);
        cvKebBaru = findViewById(R.id.card_pool_kebbaru);
        cvKebLama = findViewById(R.id.card_pool_keblama);
        cvMampang = findViewById(R.id.card_pool_mampang);
        cvPancoran = findViewById(R.id.card_pool_pancoran);
        cvPsMinggu = findViewById(R.id.card_pool_psminggu);
        cvPesanggrahan = findViewById(R.id.card_pool_pesanggrahan);
        cvSetiaBudi = findViewById(R.id.card_pool_setiabudi);
        cvTebet = findViewById(R.id.card_pool_tebet);

        cvCilandak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kecamatan = "Cilandak";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvJagakarsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "Jagakarsa";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvKebBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "KebBaru";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvKebLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "KebLama";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvMampang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "Mampang";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvPancoran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "Pancoran";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvPsMinggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "Pasar Minggu";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvPesanggrahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "Pesanggrahan";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvSetiaBudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "Setia Budi";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });

        cvTebet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kecamatan = "Tebet";

                Intent intent = new Intent(KecamatanActivity.this, PoolActivity.class);
                intent.putExtra("kecamatan", kecamatan);
                startActivity(intent);
            }
        });
    }
}
