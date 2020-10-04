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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class GalleryImage extends AppCompatActivity {\
    private TextView mTextViewResult;
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

//        mTextViewResult = findViewById(R.id.ranking);
//        Button buttonNext = findViewById(R.id.button_next_gl);
//        mQueue = Volley.newRequestQueue(this);
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jsonParse();
//            }
//        });
//    }
//    private void jsonParse(){
//        String url ="";
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        JSONArray jsonArray = response.getJSONArray("");
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });

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