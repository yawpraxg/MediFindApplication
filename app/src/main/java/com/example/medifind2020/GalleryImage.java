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
    private Bitmap bitmap;
    private Button btnBack, btnNext;
    private ImageView imageView;
    private String command;
    private int INPUT_SIZE = 200;
    private Uri image;
    private String result;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    // The first url is mocking api.
    // The second the real api from python
    //private String url = "https://jsonplaceholder.typicode.com/posts";
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
        System.out.println("testtest");


        mTextViewResult = findViewById(R.id.ranking);
        mQueue = Volley.newRequestQueue(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Start Onclick");

                sendAndRequestResponse(bitmap); //Volley Mild
                progressDialog = new ProgressDialog(GalleryImage.this);
//                Intent intent = new Intent(GalleryImage.this, ShowResult.class);
//                startActivity(intent);
//                progressDialog.setMessage("Uploading, please wait...");
//                progressDialog.show();
//                sendLeave();
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
//
        private void sendAndRequestResponse (final Bitmap bitma) {
// ref na pai do saaaa: https://www.maxester.com/blog/2019/10/04/upload-file-image-to-the-server-using-volley-in-android/
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
                                        Intent intent = new Intent(GalleryImage.this, ShowResult.class);
                                        intent.putExtra("MedKey", obj.getString("result"));
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
    //private class VolleyMultipartRequest {


////////////////////Retrofit/////////////////////////////////////////////

//    public void setBtnNext(Button btnNext) {
//        this.btnNext = btnNext;
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadImage();
//            }
//        });
//    }
//
//    private void uploadImage() {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
//        byte[] imageInByte = byteArrayOutputStream.toByteArray();
//
//        String encodeImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
//
//        Call<ResponsePOJO> call = RetroClient.getInstance().getApi().uploadImage(encodeImage);
//        call.enqueue(new Callback<ResponsePOJO>() {
//            @Override
//            public void onResponse(Call<ResponsePOJO> call, retrofit2.Response<ResponsePOJO> response) {
//                Toast.makeText(GalleryImage.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();
//
//                if (response.body().getRemarks() != null) {
//                    log.info("yay");
//                    //return;
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
//                Toast.makeText(GalleryImage.this, "Network Failed", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//public void sendLeave(){
//    progressDialog.show();
//    MultipartRequest multipartRequest = new MultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
//        @Override
//        public void onResponse(NetworkResponse response) {
//            Log.d("Status", "Successful");
//        }
//    }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Log.d("Status", "Error");
//        }
//    }){
//        @Override
//        protected Map<String, String> getParams() throws AuthFailureError {
//            Map<String,String> params = new HashMap<>();
////            params.put("parent_id", SharedPreferenceManager.getmInstance(getActivity()).getID());
////            params.put("student_id",studentid);
////            params.put("reason",txtReason.getText().toString());
////            params.put("start_date",txtFromDate.getText().toString());
////            params.put("end_date",txtToDate.getText().toString());
//
////            int i=0;
////            for (String temp: teacherid){
////                params.put("teacher_id["+(i++)+"]", temp);
////            }
//            return params;
//        }
//
//        @Override
//        protected Map<String, MultipartRequest.DataPart> getByteData() {
//            Map<String, MultipartRequest.DataPart> params = new HashMap<>();
//
//            params.put("img", new MultipartRequest.DataPart());
//            return params;
//        }
//
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//            Ma p<String,String> headers = super.getHeaders();
//            if (headers == null || headers.equals(Collections.<String, String>emptyMap())){
//                headers = new HashMap<String, String>();
//            }
////            MyApp.get().addSessionCookie(headers);
//            return headers;
//        }
//    };
//    RequestQueue queue = Volley.newRequestQueue(getContext());
//    queue.add(multipartRequest);
}


