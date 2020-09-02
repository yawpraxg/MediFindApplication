package com.example.medifind2020;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 0;
    private static final int ACTION_IMAGE_CAPTURE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;
    private static final int INPUT_SIZE = 200;
    private static final int PERMISSION_CAMERA_REQUEST_CODE = 300;
    private Uri imageUri;
    private File photoFile = null;
    private String directoryPath;


    //View
    ImageView imageView;

    FloatingActionButton fab_main, fab_one, fab_two, fab_three;
    Float translationYaxis = 100f;
    Boolean menuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    //@Nullable
    @Override
    //public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShowMenu();

        //Initializing views
        imageView = findViewById(R.id.show_image_gallery);

        if(checkPermission() == false){
            verifyPermission();
        }
    }
    private boolean checkPermission(){
        String permission = Manifest.permission.CAMERA;
        if(ContextCompat.checkSelfPermission(MainActivity.this,permission) != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return true;
    }



    private void verifyPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA},PERMISSION_CAMERA_REQUEST_CODE);
    }

    private void getActivity() {
    }

    private void ShowMenu() {
        fab_main = findViewById(R.id.fab_main);
        fab_one = findViewById(R.id.fab_one);
        fab_two = findViewById(R.id.fab_two);
        fab_three = findViewById(R.id.fab_three);

        fab_one.setAlpha(0f);
        fab_two.setAlpha(0f);
        fab_three.setAlpha(0f);

        fab_one.setTranslationY(translationYaxis);
        fab_two.setTranslationY(translationYaxis);
        fab_three.setTranslationY(translationYaxis);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuOpen) {
                    CloseMenu();
                } else {
                    OpenMenu();
                }
            }
        });

        fab_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Camera", Toast.LENGTH_SHORT).show();
                try {
                    Intent cameraView = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //cameraView.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    //startActivity(cameraView);
                    startActivityForResult(cameraView, CAMERA_REQUEST_CODE);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        fab_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
                Intent gallery = new Intent(Intent.ACTION_PICK);
                //gallery.putExtra("resId", R.id.show_image_gallery);
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"Gallery"), GALLERY_REQUEST_CODE);
            }
        });
        fab_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void OpenMenu() {
        menuOpen = ! menuOpen;
        fab_main.setImageResource(R.drawable.ic_close);
        fab_one.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab_two.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab_three.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();

    }
    private void CloseMenu() {
        menuOpen = ! menuOpen;
        fab_main.setImageResource(R.drawable.ic_add);
        fab_one.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab_two.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab_three.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ACTION_IMAGE_CAPTURE && resultCode == RESULT_OK ){
            Bundle extras = data.getExtras();
            Bitmap imageBitmapCamera = (Bitmap) extras.get("data");
            imageBitmapCamera = Bitmap.createScaledBitmap(imageBitmapCamera, INPUT_SIZE, INPUT_SIZE, false);

            Intent intent = new Intent(MainActivity.this, GalleryImage.class);
            intent.putExtra("photo", imageBitmapCamera);
            startActivity(intent);
            //Uri imageData = data.getData();
            //imageView.setImageURI(imageData);


        }else if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri imageData = data.getData();
            try{
                Bitmap imageBitmapGallery = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), imageData);
                imageBitmapGallery = Bitmap.createScaledBitmap(imageBitmapGallery, INPUT_SIZE, INPUT_SIZE, false);

                Intent intent = new Intent(MainActivity.this, GalleryImage.class);
                intent.putExtra("photo", imageBitmapGallery);
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = MainActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
           imageFileName,
           ".jpg",
           storageDir
        );
        directoryPath = image.getAbsolutePath();
        return image;

    }

    private void openCameraIntent(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = MainActivity.this.getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }
    private void galleryAddImage(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(directoryPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        MainActivity.this.sendBroadcast(mediaScanIntent);
    }
    public Uri getImageUri(Context inContext, Bitmap inImage){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage,"Title", null);
        return Uri.parse(path);

    }

}

