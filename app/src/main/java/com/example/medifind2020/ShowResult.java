package com.example.medifind2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowResult extends AppCompatActivity {
    private String result;

    TextView mBrand, mGeneric, mSize, mColor, mProperties, mDosage, mSideEffects;
    ImageView image;
    FirebaseFirestore firebaseFirestore;
<<<<<<< HEAD
    MedItem medItem;
=======
//    DocumentSnapshot documentSnapshot;
//    MedItem medItem;
>>>>>>> master

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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


//        result = this.getIntent().getStringExtra("result");
//        if (result != null){
//            Toast.makeText(this, "result" +result, Toast.LENGTH_SHORT).show();
//        }else {
//        }

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Med_des");

        //String MedKey = getIntent().getStringExtra("MedKey");

    }
}