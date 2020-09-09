package com.example.medifind2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;

    private MedAdapter adapter;
    private ArrayList<MedItem> medItems = new ArrayList<>();

    private SearchView searchView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.search_view);

//        //Query
//        Query query = firebaseFirestore.getInstance().collection("Med_des");
//
//        //RecyclerOptions
//        FirestoreRecyclerOptions<List> options = new FirestoreRecyclerOptions.Builder<List>().setQuery(query, List.class).build();

//        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<List, ListHolder>(options) {
//            @NonNull
//            @Override
//            public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
//                return new ListHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull ListHolder holder, int position, @NonNull List model) {
//                holder.name.setText(model.getName());
//                holder.gen_name.setText(model.getGenName());
//                holder.color.setText(model.getColor());
//            }
//        };
        adapter = new MedAdapter(this, medItems);

        //manually get data from firestore
        firebaseFirestore.collection("Med_des")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    String TAG = "FirestoreDebug";
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            medItems.clear();

                            // process the result from firebase, put into the 'result' ArrayList
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> rawData = document.getData();
                                medItems.add(new MedItem(rawData));
                                Log.d(TAG, document.getId() + " => " + document.getData().keySet().toString());
                            }

                            // give data to the adapter
                            adapter.setMedItems(medItems);

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        adapter.startListening();


        // setup search box
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO: make this work
                adapter.setSearchKeyword(newText);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapter.stopListening();
    }
}