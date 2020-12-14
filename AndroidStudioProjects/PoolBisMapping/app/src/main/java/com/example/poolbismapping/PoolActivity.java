package com.example.poolbismapping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.poolbismapping.Holder.PoolVH;
import com.example.poolbismapping.Model.PoolModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PoolActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar progressBar;

    private DatabaseReference poolRef;

    private String kecamatan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);

        progressBar = findViewById(R.id.pb_pool);

        kecamatan = getIntent().getStringExtra("kecamatan");

        poolRef = FirebaseDatabase.getInstance().getReference().child(kecamatan);

        recyclerView = findViewById(R.id.rv_pool);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<PoolModel> options = new FirebaseRecyclerOptions.Builder<PoolModel>()
                .setQuery(poolRef, PoolModel.class)
                .build();

        FirebaseRecyclerAdapter<PoolModel, PoolVH> adapter = new FirebaseRecyclerAdapter<PoolModel, PoolVH>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PoolVH poolVH, int i, @NonNull final PoolModel poolModel) {

                if (progressBar != null){

                    progressBar.setVisibility(View.GONE);
                }

                poolVH.textViewCardPool.setText(poolModel.getNama());
                Picasso.get().load(poolModel.getImage()).into(poolVH.imageViewCardPool);

                poolVH.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(PoolActivity.this, PoolDetailActivity.class);
                        intent.putExtra("pid", poolModel.getPid());
                        intent.putExtra("kecamatan", poolModel.getKecamatan());
                        intent.putExtra("latitude",poolModel.getLat());
                        intent.putExtra("longitude",poolModel.getLongi());
                        intent.putExtra("nama", poolModel.getNama());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public PoolVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pool_cardview, parent, false);
                PoolVH holder = new PoolVH(view);

                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
