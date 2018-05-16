package com.rxjavademo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroApi {
    public static  String baseUrl= "https://api.douban.com/v2/movie/";
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    Api api=retrofit.create(Api.class);
    retrofit2.Call<MovieSubject> call=api.getInFormation(0,20);


}
