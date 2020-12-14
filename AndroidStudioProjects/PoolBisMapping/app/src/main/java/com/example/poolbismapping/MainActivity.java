package com.example.poolbismapping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poolbismapping.Add.AddActivity;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    SliderLayout sliderLayout;
    private CardView cardNavPool, cardNavTerminal, cardNavAbout, cardNavLocation;

    private String saveCurrentDate, saveCurrentDay;

    private TextView txtHari, txtTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar-------------------------------------------------------------------------------------------
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        txtTanggal = findViewById(R.id.txt_tanggal);
        txtHari = findViewById(R.id.txt_hari);

        drawerLayout = findViewById(R.id.home_drawer_layout);
        NavigationView navigationView = findViewById(R.id.navView_home);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_draw_open, R.string.nav_draw_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        sliderLayout = findViewById(R.id.slider_layout);
        sliderLayout.setScrollTimeInSec(2);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.THIN_WORM);
        sliderLayout.setPagerIndicatorVisibility(false);
        setSliderView();

        //button card navigation
        cardNavPool = findViewById(R.id.card_main_pool);
        cardNavTerminal = findViewById(R.id.card_main_terminal);
        cardNavAbout = findViewById(R.id.card_main_about);
        cardNavLocation = findViewById(R.id.card_main_location);

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMMM, dd - yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentDay = new SimpleDateFormat("EEEE,");
        saveCurrentDay = currentDay.format(calendar.getTime());

        txtHari.setText(saveCurrentDay);
        txtTanggal.setText(saveCurrentDate);

        buttonNav();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            System.exit(0);
        }

    }

    private void setSliderView() {

        for (int i=0; i<5; i++){

            DefaultSliderView sliderView = new DefaultSliderView(MainActivity.this);

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://images.unsplash.com/photo-1494515843206-f3117d3f51b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
                    sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case   1:
                    sliderView.setImageUrl("https://www.productionparadise.com/newsletters/1443/photos/77057/original/-mg-8879-working2-x.jpg");
                    sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case   2:
                    sliderView.setImageUrl("https://bus-truck.id/image/load/640/360/gallery/sc1970.jpg");
                    sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case   3:
                    sliderView.setImageUrl("https://tonyyoungphotography.com/images/easyblog_articles/9/b2ap3_large_Galaxy-1.jpg");
                    sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case   4:
                    sliderView.setImageUrl("https://images.unsplash.com/photo-1544620347-c4fd4a3d5957?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
                    sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }

    private void buttonNav() {
        cardNavPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KecamatanActivity.class);
                startActivity(intent);
            }
        });

        cardNavTerminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TerminalActivity.class);
                startActivity(intent);
            }
        });

        cardNavAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

            }
        });

        cardNavLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LokasiActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_exit){

            System.exit(0);

        } else if (item.getItemId() == R.id.nav_add_pool){

            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.nav_add_terminal){

            Intent intent = new Intent(MainActivity.this, AddTerminalActivity.class);
            startActivity(intent);

        }

        return false;
    }
}
