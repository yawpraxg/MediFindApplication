package com.example.medifind2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

public class TargetActivity extends AppCompatActivity {

    TextView mBrand, mGeneric, mSize, mColor, mProperties, mDosage, mSideEffects;
    ImageView uploadedImage, resultImage, back;
    FirebaseFirestore firebaseFirestore;
    CollectionReference ref;
    GalleryImage galleryImage = new GalleryImage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        mBrand = findViewById(R.id.brand_name_value);
        mGeneric = findViewById(R.id.generic_name_value);
        mSize = findViewById(R.id.size_value);
        mColor = findViewById(R.id.color_value);
        mProperties = findViewById(R.id.properties_value);
        mDosage = findViewById(R.id.dosage_value);
        mSideEffects = findViewById(R.id.side_effects_value);
        resultImage = findViewById(R.id.show_result_imageResult);
        uploadedImage = findViewById(R.id.original_imageResult);
        final ImageSlider imageSlider = findViewById(R.id.slider);
        back = findViewById(R.id.target_back);

        uploadedImage.setImageBitmap(galleryImage.bitmap);

        firebaseFirestore = FirebaseFirestore.getInstance();
        ref = firebaseFirestore.collection("Med_des");

        String MedKey = getIntent().getStringExtra("MedKey");

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
                    Picasso.get().load(imageURL).into(resultImage);
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
    }
}