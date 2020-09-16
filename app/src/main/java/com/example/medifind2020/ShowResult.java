package com.example.medifind2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class ShowResult extends AppCompatActivity {

    TextView mBrand, mGeneric, mSize, mColor, mProperties, mDosage, mSideEffects;
    ImageView image;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        mBrand = findViewById(R.id.brand_name_value);
        mGeneric = findViewById(R.id.generic_name_value);
        mSize = findViewById(R.id.size_value);
        mColor = findViewById(R.id.color_value);
        mProperties = findViewById(R.id.properties_value);
        mDosage = findViewById(R.id.dosage_value);
        mSideEffects = findViewById(R.id.side_effects_value);
        image = findViewById(R.id.show_result_imageResult);


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Med_des");

        String MedKey = getIntent().getStringExtra("MedKey");

        firebaseFirestore.document("MedKey");


    }
}