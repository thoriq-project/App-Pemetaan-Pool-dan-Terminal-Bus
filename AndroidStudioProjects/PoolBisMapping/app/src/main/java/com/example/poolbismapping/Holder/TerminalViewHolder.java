package com.example.poolbismapping.Holder;

import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poolbismapping.Listener.ItemClickListener;
import com.example.poolbismapping.R;

import java.util.logging.LogRecord;

public class TerminalViewHolder extends RecyclerView.ViewHolder implements ItemClickListener, Filterable {

    public ImageView imageViewTerminal;
    public TextView textViewNamaTerminal, textViewAlamatTerminal, textViewKecTerminal, textViewAKAP, textViewDalkot, textViewJabo, textViewDesk, textViewNamaBanner;
    public ItemClickListener listener;

    public TerminalViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewTerminal = itemView.findViewById(R.id.img_card_terminal);

        textViewNamaTerminal = itemView.findViewById(R.id.txt_card_nama_terminal);
        textViewAlamatTerminal = itemView.findViewById(R.id.txt_card_alamat_terminal);
        textViewKecTerminal = itemView.findViewById(R.id.txt_card_kecamatan_terminal);
        textViewAKAP = itemView.findViewById(R.id.txt_card_terminal_akap);
        textViewDalkot = itemView.findViewById(R.id.txt_card_terminal_dalkot);
        textViewJabo = itemView.findViewById(R.id.txt_card_terminal_jabodetabek);
        textViewDesk = itemView.findViewById(R.id.txt_card_deskripsi_terminal);
        textViewNamaBanner = itemView.findViewById(R.id.txt_nama_terminal_banner);


    }

    private void setItemClickListener (ItemClickListener listener){

        this.listener = listener;

    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {

        listener.onClick(view, getAdapterPosition(), false);

    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
