package com.example.medifind2020;

<<<<<<< HEAD
public interface Api {
=======
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("")
    Call<ResponsePOJO> uploadImage(
            @Field("EN_IMAGE") String encodeImage
    );
>>>>>>> 3551008d0a22a56b2e11411004bdeb0b474f08b6
}
