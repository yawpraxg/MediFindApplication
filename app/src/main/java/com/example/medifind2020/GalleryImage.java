package com.example.medifind2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;

public class GalleryImage extends AppCompatActivity {
    private RequestQueue mQueue;
    private Bitmap bitmap;
    private ImageView imageView;
    private String command;
    private int INPUT_SIZE = 200;
    private Uri image;
    private String result;

    @Override
    protected void  onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        bitmap  = this.getIntent().getParcelableExtra("photo");
        imageView = findViewById(R.id.show_image_gallery);
        imageView.setImageBitmap(bitmap);
        result = null;
        mTextViewResul = findViewById(R.id. );
        Button buttonNext = findViewById(R.id.button_next_gl);
        buttonNext.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //return false;
                jsonParse();
            }
        });
    }
    private void jsonParse(){
        String url ="";
    }
    //ImageView i1;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gallery_image);
//
//        //Initializing views
//        i1 = (ImageView) findViewById(R.id.show_image_gallery);
//
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null){
//            int resid = bundle.getInt("resId");
//            i1.setImageResource(resid);
//        }
//    }
}