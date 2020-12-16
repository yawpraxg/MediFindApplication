package com.example.medifind2020;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GalleryImage extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 11 ;
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    Bitmap bitmap;
    private Button btnBack, btnNext;
    ImageView imageView;
    private String command;
    private int INPUT_SIZE = 300;
    private Uri image;
    private String result;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    // The first url is mocking api.
    // The second the real api from python
    private String url = "https://medifindapi.herokuapp.com/predict";
    private ArrayList<MedItem> medItems = new ArrayList<>();
    private Button back;

    ProgressDialog progressDialog;

    final static Logger log = Logger.getLogger(String.valueOf(GalleryImage.class));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        bitmap = this.getIntent().getParcelableExtra("photo");
        imageView = findViewById(R.id.show_image_gallery);
        imageView.setImageBitmap(bitmap);
        btnNext = findViewById(R.id.button_next_gl);
        back = findViewById(R.id.button_back_gl);

        result = null;

        mTextViewResult = findViewById(R.id.ranking);
        mQueue = Volley.newRequestQueue(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Start Onclick");

                sendAndRequestResponse(bitmap); //Volley Mild
                progressDialog = new ProgressDialog(GalleryImage.this);
                progressDialog.setMessage("Uploading, please wait...");
                progressDialog.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//////////////////////////////////Volley///////////////////////////////////

        private void sendAndRequestResponse (final Bitmap bitma) {
            // reference: https://www.maxester.com/blog/2019/10/04/upload-file-image-to-the-server-using-volley-in-android/
            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            try {
                                JSONObject obj = new JSONObject(new String(response.data));
                                System.out.println(obj.toString());
                                if (obj.get("result") != null) {
                                    // TODO call process after get name
                                    Toast.makeText(getApplicationContext(), obj.getString("result"), Toast.LENGTH_SHORT).show();
                                    if (obj.get("result") != null) {
                                        Intent intent = new Intent(GalleryImage.this, TargetActivity.class);
                                        intent.putExtra("MedKey", obj.getString("result"));
                                        intent.putExtra("image", bitmap);
                                        startActivity(intent);
                                    }
                                } else if("error" != null){
                                    //TODO process after get error No file
                                    Toast.makeText(getApplicationContext(), obj.getString("error"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("GotError","No result"+error.getMessage());
                        }
                    }) {
//
//
                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    long imagename = System.currentTimeMillis();
                    params.put("image", new DataPart(imagename + ".jpg", getFileDataFromDrawable(bitma)));
                    return params;
                }
            };

            //adding the request to volley
            Volley.newRequestQueue(this).add(volleyMultipartRequest);
        }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}


