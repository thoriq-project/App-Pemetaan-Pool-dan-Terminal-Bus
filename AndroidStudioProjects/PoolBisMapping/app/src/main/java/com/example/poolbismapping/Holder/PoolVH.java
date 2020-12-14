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

public class PoolVH extends RecyclerView.ViewHolder implements ItemClickListener, Filterable {

    public ImageView imageViewCardPool;
    public TextView textViewCardPool;
    public ItemClickListener listener;

    public PoolVH(@NonNull View itemView) {
        super(itemView);

        imageViewCardPool = itemView.findViewById(R.id.img_card_pool);
        textViewCardPool = itemView.findViewById(R.id.txt_card_nama_pool);
    }

    public void setItemClickListener (ItemClickListener listener){

        this.listener = listener;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {

        listener.onClick(view, getAdapterPosition(), false);

    }
}
