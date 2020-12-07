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

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.google.firebase.firestore.SnapshotMetadata;
import com.squareup.picasso.Picasso;
import com.denzcoskun.imageslider.constants.ScaleTypes;

public class ShowResult extends AppCompatActivity {
    private String result;
    DatabaseReference reff;

    TextView mBrand, mGeneric, mSize, mColor, mProperties, mDosage, mSideEffects;
    ImageView image, back;
    FirebaseFirestore firebaseFirestore;
    String userId;
    //private Context documentSnapshot;
    //private ResourceBundle documentSnapshot;
    CollectionReference ref;
    MedItem medItem;

    private int[] mImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //firebaseFirestore = FirebaseFirestore.getInstance();
        //firebaseFirestore.collection("Med_des");

        setContentView(R.layout.activity_show_result);
        mBrand = findViewById(R.id.brand_name_value);
        mGeneric = findViewById(R.id.generic_name_value);
        mSize = findViewById(R.id.size_value);
        mColor = findViewById(R.id.color_value);
        mProperties = findViewById(R.id.properties_value);
        mDosage = findViewById(R.id.dosage_value);
        mSideEffects = findViewById(R.id.side_effects_value);
        final ImageSlider imageSlider = findViewById(R.id.slider);
        back = findViewById(R.id.show_result_back);

//        result = this.getIntent().getStringExtra("result");
//        if (result != null){
//            Toast.makeText(this, "result" +result, Toast.LENGTH_SHORT).show();
//        }else {
//        }

        firebaseFirestore = FirebaseFirestore.getInstance();
        ref = firebaseFirestore.collection("Med_des");

        String MedKey = getIntent().getStringExtra("MedKey");

//        List<String> slideModels = new ArrayList<>();

        ref.document(MedKey).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()) {
                    List<SlideModel> slideModels = new ArrayList<>();
                    String name = value.get("name").toString();
                    String gen_name = value.get("gen_name").toString();
                    String color = value.get("color").toString();
                    String size = value.get("size").toString();
                    String properties = "      " + value.get("prop").toString();
                    String dosage = "     " + value.get("dosage").toString();
                    String side_eff = "     " + value.get("side_eff").toString();
                    String imageURL = value.get("thumbnail").toString();
                    String img1 = value.get("img1").toString();
                    String img2 = value.get("img2").toString();

                    mBrand.setText(name);
                    mGeneric.setText(gen_name);
                    mSize.setText(size);
                    mColor.setText(color);
                    mProperties.setText(properties);
                    mDosage.setText(dosage);
                    mSideEffects.setText(side_eff);
//                    Picasso.get().load(imageURL).into(image);
                    slideModels.add(new SlideModel(imageURL, ScaleTypes.CENTER_INSIDE));
                    slideModels.add(new SlideModel(img1, ScaleTypes.CENTER_INSIDE));
                    slideModels.add(new SlideModel(img2, ScaleTypes.CENTER_INSIDE));
                    imageSlider.setImageList(slideModels);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //String MedKey = getIntent().getStringExtra("MedKey");

    }
}