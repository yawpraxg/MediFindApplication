package com.example.medifind2020;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    ArrayList<List> list;
    public AdapterClass(ArrayList<List> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.brand.setText(list.get(i).getBrandName());
        myViewHolder.general.setText(list.get(i).getGenName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView brand, general;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.brand_name);
            general = itemView.findViewById(R.id.generic_name);
        }
    }
}
