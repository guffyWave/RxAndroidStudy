package com.example.gufran.rxandroidstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickMe(View view) {
        Observable<String> observable = Observable.just("Hello RxGufran");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "onCompleted ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, "Hey " + s, Toast.LENGTH_SHORT).show();
            }
        };

        Subscription mySubscription = observable.subscribe(observer);

    }


}
