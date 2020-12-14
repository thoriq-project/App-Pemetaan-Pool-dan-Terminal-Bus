package com.example.poolbismapping.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.poolbismapping.R;

public class AddActivity extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initButton();
        navButton();
    }

    private void initButton() {
        button1 = findViewById(R.id.btn_cilandak);
        button2 = findViewById(R.id.btn_jagakarsa);
        button3 = findViewById(R.id.btn_kebbaru);
        button4 = findViewById(R.id.btn_keblama);
        button5 = findViewById(R.id.btn_mampang);
        button6 = findViewById(R.id.btn_pancoran);
        button7 = findViewById(R.id.btn_pasarminggu);
        button8 = findViewById(R.id.btn_pesanggrahan);
        button9 = findViewById(R.id.btn_setiabudi);
        button10 = findViewById(R.id.btn_tebet);
    }


    private void navButton() {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cilandak = "Cilandak";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", cilandak);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jagakarsa = "Jagakarsa";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", jagakarsa);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kebbaru = "KebBaru";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", kebbaru);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keblama = "KebLama";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", keblama);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mampang = "Mampang";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", mampang);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pancoran = "Pancoran";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", pancoran);
                startActivity(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pasarminggu = "Pasar Minggu";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", pasarminggu);
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesanggrahan = "Pesanggrahan";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", pesanggrahan);
                startActivity(intent);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setiabudi = "Setia Budi";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", setiabudi);
                startActivity(intent);
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tebet = "Tebet";

                Intent intent = new Intent(AddActivity.this, AddPoolActivity.class);
                intent.putExtra("kecamatan", tebet);
                startActivity(intent);
            }
        });
    }
}
