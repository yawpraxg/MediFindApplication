package com.example.medifind2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;

    private MedAdapter adapter;
    private ArrayList<MedItem> medItems = new ArrayList<>();

    private SearchView searchView;
    private TextView noItem;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("Med_des").getParent();

        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.search_view);
        noItem = findViewById(R.id.no_item);
        back = findViewById(R.id.search_back);

        TextView brand = findViewById(R.id.brand_name_value);

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
                                String id = document.getId();

                                medItems.add(new MedItem(rawData, id));
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

        adapter.setOnItemClickListener(new MedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MedItem medItem, int position) {
                String name = medItem.getName();
//                Toast.makeText(SearchActivity.this, medItem.id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchActivity.this, ShowResult.class);
                intent.putExtra("MedKey", medItem.id);
                startActivity(intent);
            }
        });

//        adapter.setOnItemClickListener(new MedAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//                MedItem medItem = documentSnapshot.toObject(MedItem.class);
//                String id = documentSnapshot.getId();
//                String path = documentSnapshot.getReference().getPath();
//                Toast.makeText(SearchActivity.this, "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(SearchActivity.this, ShowResult.class );
//                startActivity(intent);
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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