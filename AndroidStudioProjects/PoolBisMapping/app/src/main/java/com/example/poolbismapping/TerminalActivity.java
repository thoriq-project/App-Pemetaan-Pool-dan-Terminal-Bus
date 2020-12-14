package com.example.poolbismapping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.poolbismapping.Holder.TerminalViewHolder;
import com.example.poolbismapping.Model.Terminal;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class  TerminalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private DatabaseReference terminalRef;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal);

        recyclerView = findViewById(R.id.rv_terminal);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        terminalRef = FirebaseDatabase.getInstance().getReference().child("Terminal");

        progressBar = findViewById(R.id.pb_terminal);

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Terminal> options =new FirebaseRecyclerOptions.Builder<Terminal>()
                .setQuery(terminalRef, Terminal.class)
                .build();

        FirebaseRecyclerAdapter<Terminal, TerminalViewHolder> adapter = new FirebaseRecyclerAdapter<Terminal, TerminalViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TerminalViewHolder holder, int i, @NonNull final Terminal terminal) {

                if (progressBar != null) {

                    progressBar.setVisibility(View.GONE);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(TerminalActivity.this, TerminalDetailActivity.class);
                        intent.putExtra("tid", terminal.getTid());
                        intent.putExtra("lat", terminal.getLat());
                        intent.putExtra("longi", terminal.getLongi());
                        intent.putExtra("nama", terminal.getNama());
                        startActivity(intent);
                    }
                });

                if (terminal.getAkap().isEmpty()){

                    holder.textViewAKAP.setVisibility(View.GONE);

                } else if (terminal.getDalkot().isEmpty()){

                    holder.textViewDalkot.setVisibility(View.GONE);

                } else if (terminal.getJabo().isEmpty()){

                    holder.textViewJabo.setVisibility(View.GONE);
                }

                holder.textViewNamaTerminal.setText(terminal.getNama());
                holder.textViewAlamatTerminal.setText(terminal.getAlamat());
                holder.textViewKecTerminal.setText(terminal.getKecamatan());
                holder.textViewAKAP.setText(terminal.getAkap());
                holder.textViewDalkot.setText(terminal.getDalkot());
                holder.textViewJabo.setText(terminal.getJabo());
                holder.textViewDesk.setText(terminal.getDeskripsi());
                holder.textViewNamaBanner.setText(terminal.getNama());
                Picasso.get().load(terminal.getImage()).into(holder.imageViewTerminal);



            }

            @NonNull
            @Override
            public TerminalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.terminal_cardview, parent, false);
                TerminalViewHolder holder = new TerminalViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
