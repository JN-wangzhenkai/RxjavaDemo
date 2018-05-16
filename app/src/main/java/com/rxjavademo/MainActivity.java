package com.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private String TAG = "mainactivity";
    public static String baseUrl = "https://api.douban.com/v2/movie/";
    private List<MovieSubject> mlsit = new ArrayList<>();
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {

                Log.d(TAG, "onNext: observer");
            }

        };

        Subscriber<String> mysubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onComplete: subscribe");
            }


            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "ononError: ubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: subscribe11" + s);
            }

            @Override
            public void onStart() {
                Log.d(TAG, "onstart: subscribe");
            }

        };


        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber1) {

                subscriber1.onNext("Hello");
                subscriber1.onNext("Hi");
                subscriber1.onNext("Aloha");
                subscriber1.onCompleted();
            }
        });

        observable.subscribe(observer);

        observable.subscribe(mysubscriber);



        RecyclerView recyslerview=findViewById(R.id.recycle_view);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyslerview.setLayoutManager(layoutmanager);

//        adapter = new MovieAdapter(mlsit,this);
//        recyslerview.setAdapter(adapter);


        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        retrofit2.Call<MovieSubject> call = api.getInFormation(0, 20);

        call.enqueue(new Callback<MovieSubject>() {
            @Override
            public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {

                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG, "onResponse: "+call);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieSubject> call, Throwable t) {

            }
        });


    }

}
