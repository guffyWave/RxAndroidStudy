package com.example.gufran.rxandroidstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    String TAG = "GUFRAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickMe(View view) {
        Observable<String> observable = Observable.just("Hello Rx Gufran");

//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Toast.makeText(MainActivity.this, "onCompleted ", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNext(String s) {
//                Toast.makeText(MainActivity.this, "Hey " + s, Toast.LENGTH_SHORT).show();
//            }
//        };

        //        Subscription mySubscription = observable.subscribe(observer);

        Action1 action1 = new Action1() {
            @Override
            public void call(Object o) {
                Log.d(TAG, "" + ((String) o));
            }
        };


        Subscription mySubscription = observable.subscribe(action1);
        //mySubscription.unsubscribe();
    }

    public void clickMe2(View view) {
        Observable myArrayObservable = Observable.from(new Integer[]{5, 7, -4, 9, 2});


//        myArrayObservable = myArrayObservable.map(new Func1<Integer, Integer>() { // Input and Output are both Integer
//            @Override
//            public Integer call(Integer integer) {
//                return integer * integer; // Square the number
//            }
//        });

        myArrayObservable
                .skip(2) // Skip the first two items
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) { // Ignores any item that returns false
                        return integer % 2 == 0;
                    }
                });


        myArrayObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "Action1 call : " + integer);
            }
        });
    }

    public void clickMe3(View v) {

        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("Gufran Emitted");
                subscriber.onCompleted();
            }
        });


        myObservable.subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(MainActivity.this, "Hello " + s, Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
