package com.example.medifind2020;

<<<<<<< HEAD
public class RetroClient {
}
=======
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static final String BASE_URL = "";
    private static RetroClient myClient;
    private Retrofit retrofit;

    private  RetroClient(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized RetroClient getInstance(){

        if(myClient == null){
            myClient = new RetroClient();
        }

        return myClient;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}

>>>>>>> 3551008d0a22a56b2e11411004bdeb0b474f08b6
