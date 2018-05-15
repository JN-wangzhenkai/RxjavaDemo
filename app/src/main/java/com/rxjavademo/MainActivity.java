package com.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private String  TAG="mainactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Observer<String>observer=new Observer<String>() {
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

        Subscriber<String> mysubscriber=new Subscriber<String>() {
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
                Log.d(TAG, "onNext: subscribe11"+s);
            }

            @Override
            public void onStart() {
                Log.d(TAG, "onstart: subscribe");
            }


        };


        Observable observable=Observable.create(new Observable.OnSubscribe<String>() {
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

    }
}
