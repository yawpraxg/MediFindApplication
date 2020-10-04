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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< Updated upstream
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;

=======
>>>>>>> Stashed changes
public class GalleryImage extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private Bitmap bitmap;
    private ImageView imageView;
    private String command;
    private int INPUT_SIZE = 200;
    private Uri image;
    private String result;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    // The first url is mocking api.
    // The second the real api from python
    private String url = "https://jsonplaceholder.typicode.com/posts";
    //private String url = "http://127.0.0.1:5000/upload";

    final static Logger log = Logger.getLogger(String.valueOf(GalleryImage.class));

    @Override
    protected void  onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        bitmap  = this.getIntent().getParcelableExtra("photo");
        imageView = findViewById(R.id.show_image_gallery);
        imageView.setImageBitmap(bitmap);
        result = null;
        log.info("testtest");
        sendAndRequestResponse();
//        mTextViewResult = findViewById(R.id.ranking);
//        Button buttonNext = findViewById(R.id.button_next_gl);
//        mQueue = Volley.newRequestQueue(this);
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jsonParse();
//            }
//        });
    }
//    private void jsonParse(){
////        bin = new File("E:\\meter.jpg");
//        String url ="http://127.0.0.1:5000/upload";
//        log.info(url);
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("");
//                        } catch (Exception e) {
//                            log.info(" Test Error");
//
//                            log.info(e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        })
//        ;
//
//        log.info(request.getBodyContentType());
//        log.info("request.getBodyContentType()");
//
//
//    }

    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                log.info("Sucess wa 55555555555");
                log.info(response);

                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                log.info("Error wa 55555555555");
            }
        });

        mRequestQueue.add(mStringRequest);
    }

//        });

//        thread.start();
//    }
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