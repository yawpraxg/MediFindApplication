package com.example.medifind2020;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ResourceBundle;

public class ShowResult extends AppCompatActivity {
    private String result;
    DatabaseReference reff;

    TextView mBrand, mGeneric, mSize, mColor, mProperties, mDosage, mSideEffects;
    ImageView image;
    FirebaseAuth fAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;
    //private Context documentSnapshot;
    //private ResourceBundle documentSnapshot;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Med_des");

        setContentView(R.layout.activity_show_result);
        mBrand = findViewById(R.id.brand_name_value);
        mGeneric = findViewById(R.id.generic_name_value);
        mSize = findViewById(R.id.size_value);
        mColor = findViewById(R.id.color_value);
        mProperties = findViewById(R.id.properties_value);
        mDosage = findViewById(R.id.dosage_value);
        mSideEffects = findViewById(R.id.side_effects_value);
        image = findViewById(R.id.show_result_imageResult);

        fAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        //userId = fAuth.getCurrentUser().getUid();
        FirebaseFirestore m = fAuth.getCurrentUser();
        if(m != null){
            userId = m.getUid();
        }
        final DocumentReference documentReference = firebaseFirestore.collection("Med_des").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            //private ResourceBundle documentSnapshot;

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                mBrand.setText(documentSnapshot.getString("name"));
                mGeneric.setText(documentSnapshot.getString("gen_name"));
                mSize.setText(documentSnapshot.getString("size"));
                mColor.setText(documentSnapshot.getString("color"));
                mProperties.setText(documentSnapshot.getString("prop"));
                mDosage.setText(documentSnapshot.getString("dosage"));
                mSideEffects.setText(documentSnapshot.getString("side_eff"));

            }
        });


//        reff = FirebaseDatabase.getInstance().getReference().child("Member").child("l");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String brand = dataSnapshot.child("name").getValue().toString();
//                String generic = dataSnapshot.child("gen_name").getValue().toString();
//                String size = dataSnapshot.child("size").getValue().toString();
//                String color = dataSnapshot.child("color").getValue().toString();
//                String prop = dataSnapshot.child("prop").getValue().toString();
//                String dosag = dataSnapshot.child("dosage").getValue().toString();
//                String sideff = dataSnapshot.child("side_eff").getValue().toString();
//                String image = dataSnapshot.child("image").getValue().toString();
//                mBrand.setText(brand);
//                mGeneric.setText(generic);
//                mSize.setText(size);
//                mColor.setText(color);
//                mProperties.setText(prop);
//                mDosage.setText(dosag);
//                mSideEffects.setText(sideff);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//        result = this.getIntent().getStringExtra("result");
//        if (result != null){
//            Toast.makeText(this, "result" +result, Toast.LENGTH_SHORT).show();
//        }else {
//        }



        //String MedKey = getIntent().getStringExtra("MedKey");

    }
}