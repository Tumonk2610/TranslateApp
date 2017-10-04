package com.cardiomood.hoanglong.api;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.mime.TypedString;


public interface MyService {
    @Headers( "Content-Type: application/json" )
    @POST("/inputtools/request?ime=handwriting")
    public void addAction(@Body TypedString addActionSend, Callback<Response> callback);



}




