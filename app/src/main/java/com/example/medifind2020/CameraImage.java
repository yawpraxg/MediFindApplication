package com.example.medifind2020;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
public class CameraImage extends AppCompatActivity {
    private Bitmap bitmap;
    private ImageView imageView;
    private String command;
    private int INPUT_SIZE = 200;
    private Uri image;
    private String result;

    @Override
    protected void  onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_image);
        bitmap  = this.getIntent().getParcelableExtra("snap");
        imageView = findViewById(R.id.show_image_camera);
        imageView.setImageBitmap(bitmap);
        result = null;
    }
}
