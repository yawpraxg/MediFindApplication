package com.example.medifind2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.ListHolder> {

    Context c;
    ArrayList<MedItem> medItems;
    public void setMedItems(ArrayList<MedItem> medItems) {
        this.medItems = medItems;
        setSearchKeyword(this.searchKeyword);   // force filtering with keyword again
        notifyDataSetChanged();
    }
    ArrayList<MedItem> filteredMedItems =  new ArrayList<>();

    String searchKeyword = "";
    public void setSearchKeyword(String newKeyword) {
        this.searchKeyword = newKeyword.toLowerCase();

        if (newKeyword == null || newKeyword.length() == 0) {
            // no keyword provided -> show all items
            this.filteredMedItems.addAll(medItems);
        }
        else {
            // perform search
            filteredMedItems.clear();
            for (MedItem l : medItems) {
                // ...
                if ((l.name.toLowerCase().contains(newKeyword))) {
                    filteredMedItems.add(l);
                }
            }
        }

        // redraw
        notifyDataSetChanged();
    }

    public MedAdapter(Context c, ArrayList<MedItem> medItems) {
        this.c = c;
        this.medItems = medItems;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, null);
        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        holder.name.setText(filteredMedItems.get(position).getName());
        holder.gen_name.setText(filteredMedItems.get(position).getGenName());
        holder.color.setText(filteredMedItems.get(position).getColor());

//        Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
//        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
//        return medItems.size();
        return filteredMedItems.size();
    }


    class ListHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView gen_name;
        public TextView color;

        public ListHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.list_brand_name);
            this.gen_name = itemView.findViewById(R.id.list_gen_name);
            this.color = itemView.findViewById(R.id.list_color);
        }
    }
}
