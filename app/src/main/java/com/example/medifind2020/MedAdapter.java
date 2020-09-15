package com.example.medifind2020;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.firestore.DocumentSnapshot;


import java.util.ArrayList;

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.ListHolder> {
    private SearchActivity searchActivity;
    Context c;
    ArrayList<MedItem> medItems;
    //SearchActivity search = new SearchActivity();
    OnItemClickListener listener;

    public void setMedItems(ArrayList<MedItem> medItems) {
        this.medItems = medItems;
        setSearchKeyword(this.searchKeyword);   // force filtering with keyword again
        notifyDataSetChanged();
    }

    ArrayList<MedItem> filteredMedItems = new ArrayList<>();

    String searchKeyword = "";

    public void setSearchKeyword(String newKeyword) {

        this.searchKeyword = newKeyword.toLowerCase();

        if (newKeyword == null || newKeyword.length() == 0) {
            // no keyword provided -> show all items
            this.filteredMedItems.addAll(medItems);
        } else {
            // perform search
            filteredMedItems.clear();
            for (MedItem l : medItems) {
                if (l.name.toLowerCase().contains(newKeyword) ||
                        l.gen_name.toLowerCase().contains(newKeyword) ||
                        l.color.toLowerCase().contains(newKeyword)) {
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
        //this.listener = listener;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, null);
        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, final int position) {
        holder.name.setText(filteredMedItems.get(position).getName());
        holder.gen_name.setText(filteredMedItems.get(position).getGenName());
        holder.color.setText(filteredMedItems.get(position).getColor());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchActivity, ShowResult.class );
                c.startActivity(intent);
            }
        });
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(search, ShowResult.class);
//                intent.putExtra("MedKey", getItemViewType(position));
//                c.startActivity(intent);
//            }
//        });


//        Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
//        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return filteredMedItems.size();
    }


    class ListHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView gen_name;
        public TextView color;
        public ImageView image;
        View view;

        public ListHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.list_brand_name);
            this.gen_name = itemView.findViewById(R.id.list_gen_name);
            this.color = itemView.findViewById(R.id.list_color);
            this.image = itemView.findViewById(R.id.list_image);
            view = itemView;

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION && listener != null) {
//                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
//                    }
//                }
//            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
