package com.rxjavademo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("top250")
    Call<MovieSubject>getInFormation(@Query("start") int start, @Query("count")int count);
}
