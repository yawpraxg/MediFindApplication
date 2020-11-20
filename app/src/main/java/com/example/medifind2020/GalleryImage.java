package com.example.medifind2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Collections;
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

        result = null;
        //log.info("testtest");

        mTextViewResult = findViewById(R.id.ranking);
        mQueue = Volley.newRequestQueue(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendAndRequestResponse(); //Volley Mild
                progressDialog = new ProgressDialog(GalleryImage.this);
                progressDialog.setMessage("Uploading, please wait...");
                progressDialog.show();
//                sendLeave();
            }
        });
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("Click", "OnClick");
//                progressDialog = new ProgressDialog(GalleryImage.this);
//                progressDialog.setMessage("Uploading, please wait...");
//                progressDialog.show();
//                VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//                        String resultResponse = new String(response.data);
//                        System.out.println("this is seperator rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//                        System.out.println(resultResponse);
//
//                        try {
//                            JSONObject result = new JSONObject(resultResponse);
//
//                            String status = result.getString("status");
//                            String message = result.getString("message");
//
//                            if (status.equals(true)) { /*test true*/
//                                // tell everybody you have succeed upload image and post strings
//                                Log.i("Message", message);
//                            } else {
//                                Log.i("Unexpected", message);
//                            }//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        NetworkResponse networkResponse = error.networkResponse;
//                        String errorMessage = "Unknown error";
//                        if (networkResponse == null) {
//                            if (error.getClass().equals(TimeoutError.class)) {
//                                errorMessage = "Request timeout";
//                            } else if (error.getClass().equals(NoConnectionError.class)) {
//                                errorMessage = "Failed to connect server";
//                            }
//                        } else {
//                            String result = new String(networkResponse.data);
//                            try {
//                                JSONObject response = new JSONObject(result);
//                                String status = response.getString("status");
//                                String message = response.getString("message");
//
//                                Log.e("Error Status", status);
//                                Log.e("Error Message", message);
//
//                                if (networkResponse.statusCode == 404) {
//                                    errorMessage = "Resource not found";
//                                } else if (networkResponse.statusCode == 401) {
//                                    errorMessage = message+" Please login again";
//                                } else if (networkResponse.statusCode == 400) {
//                                    errorMessage = message+ " Check your inputs";
//                                } else if (networkResponse.statusCode == 500) {
//                                    errorMessage = message+" Something is getting wrong";
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        Log.i("Error", errorMessage);
//                        error.printStackTrace();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<>();
//                        //params.put("api_token", "gh659gjhvdyudo973823tt9gvjf7i6ric75r76");
////                params.put("img", imageView.getText().toString());
//                        //params.put("name", mNameInput.getText().toString());
//                        //params.put("location", mLocationInput.getText().toString());
//                        //params.put("about", mAvatarInput.getText().toString());
//                        //params.put("contact", mContactInput.getText().toString());
//                        return params;
//                    }
//
//                    @Override
//                    protected Map<String, DataPart> getByteData() {
//                        Map<String, DataPart> params = new HashMap<>();
//                        // file name could found file base or direct access from real path
//                        // for now just get bitmap data from ImageView
//                        //params.put("avatar", new DataPart("file_avatar.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mAvatarImage.getDrawable()), "image/jpeg"));
//                        //params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mCoverImage.getDrawable()), "image/jpeg"));
//                        //DataPart second parameter is byte[]
//                        return params;
//                    }
//                };
//
//                VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
//            }
//        });

//        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
//            @Override
//            public void onResponse(NetworkResponse response) {
//                String resultResponse = new String(response.data);
//                try {
//                    JSONObject result = new JSONObject(resultResponse);
//                    String status = result.getString("status");
//                    String message = result.getString("message");
//
//                    if (status.equals(true)) { /*test true*/
//                        // tell everybody you have succeed upload image and post strings
//                        Log.i("Message", message);
//                    } else {
//                        Log.i("Unexpected", message);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                NetworkResponse networkResponse = error.networkResponse;
//                String errorMessage = "Unknown error";
//                if (networkResponse == null) {
//                    if (error.getClass().equals(TimeoutError.class)) {
//                        errorMessage = "Request timeout";
//                    } else if (error.getClass().equals(NoConnectionError.class)) {
//                        errorMessage = "Failed to connect server";
//                    }
//                } else {
//                    String result = new String(networkResponse.data);
//                    try {
//                        JSONObject response = new JSONObject(result);
//                        String status = response.getString("status");
//                        String message = response.getString("message");
//
//                        Log.e("Error Status", status);
//                        Log.e("Error Message", message);
//
//                        if (networkResponse.statusCode == 404) {
//                            errorMessage = "Resource not found";
//                        } else if (networkResponse.statusCode == 401) {
//                            errorMessage = message+" Please login again";
//                        } else if (networkResponse.statusCode == 400) {
//                            errorMessage = message+ " Check your inputs";
//                        } else if (networkResponse.statusCode == 500) {
//                            errorMessage = message+" Something is getting wrong";
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Log.i("Error", errorMessage);
//                error.printStackTrace();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                //params.put("api_token", "gh659gjhvdyudo973823tt9gvjf7i6ric75r76");
////                params.put("img", imageView.getText().toString());
//                //params.put("name", mNameInput.getText().toString());
//                //params.put("location", mLocationInput.getText().toString());
//                //params.put("about", mAvatarInput.getText().toString());
//                //params.put("contact", mContactInput.getText().toString());
//                return params;
//            }
//
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                // file name could found file base or direct access from real path
//                // for now just get bitmap data from ImageView
//                //params.put("avatar", new DataPart("file_avatar.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mAvatarImage.getDrawable()), "image/jpeg"));
//                //params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mCoverImage.getDrawable()), "image/jpeg"));
//                //DataPart second parameter is byte[]
//                return params;
//            }
//        };
//
//        VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);

//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progressDialog = new ProgressDialog(GalleryImage.this);
//                progressDialog.setMessage("Uploading, please wait...");
//                progressDialog.show();
//
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] imageBytes = baos.toByteArray();
//                final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//
//                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
//                    @Override
//                    public void onResponse(String s) {
//                        progressDialog.dismiss();
//                        if(s.equals("true")){
//                            Toast.makeText(GalleryImage.this, "Uploaded Successful", Toast.LENGTH_LONG).show();
////                            log.info("dai");
//                            Log.d("Status", "Success");
//                        }
//                        else{
//                            Toast.makeText(GalleryImage.this, "Some error occurred!", Toast.LENGTH_LONG).show();
////                            log.info("maidai");
//                            //Log.d("Status", "Error");
//
//                        }
//                    }
//                },new Response.ErrorListener(){
//                    @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            Toast.makeText(GalleryImage.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();
//                            Log.d("Status", "Bok wa ERROR ngai EDOK");
//                        }
//                    }) {
//                        //adding parameters to send
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {
//                            Map<String, String> parameters = new HashMap<String, String>();
//                            //parameters.put("file", new MultipartRequest.DataPart());
//                            parameters.put("photo", imageString);
//                            return parameters;
//                    }
//                };
//
//                RequestQueue rQueue = Volley.newRequestQueue(GalleryImage.this);
//                rQueue.add(request);
//            }
//        });

    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri filePath = data.getData();
//
//            try {
//                //getting image from gallery
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//
//                //Setting image to ImageView
//                //image.setImageBitmap(bitmap);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

//        setBtnNext(buttonNext);
//
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendAndRequestResponse();
//            }
//        });
//    }


//////////////////////////////////////Multipart///////////////////////////////


    ///////////////////////////////////////////////////////////////////


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


//////////////////////////////////Volley///////////////////////////////////
//
//        private void sendAndRequestResponse () {
//            final String tags = "image";
//            String url = "http://10.17.250.83:5000/upload";
////            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
////                    new Response.Listener<NetworkResponse>() {
//                    //RequestQueue initialized
//            mRequestQueue = Volley.newRequestQueue(this);
////
////            //String Request initialized
//            mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//
//                @Override
//                public void onResponse(String response) {
//                    log.info("Success wa 55555555555");
//                    log.info(response);
//
//                    Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    log.info("Got into errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//                    System.out.println(error.getMessage());
//                }
//            });
//            mRequestQueue.add(mStringRequest);
//     }

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


