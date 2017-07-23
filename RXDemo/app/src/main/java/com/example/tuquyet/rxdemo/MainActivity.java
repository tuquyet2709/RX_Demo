package com.example.tuquyet.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextView;
    private EditText mEditText;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.text);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // demoFrom();
                // demoInterval();
                demoJust();
            }
        });
    }

    private void demoJust() {
        String s = new String();
        s = mEditText.getText().toString();
        Observable.just(s).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull String s) {
                mTextView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private void demoInterval() {
        i = 1;
        Observable.interval(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }

                @Override
                public void onNext(@NonNull Long aLong) {
                    update();
                    Log.e("------------------", "onNext: " + i);
                    if (i == 5) Schedulers.shutdown();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                }

                @Override
                public void onComplete() {
                    Log.e("", "onComplete: ");
                }
            });
    }

    private void update() {
        i++;
    }

    private void demoFrom() {
        io.reactivex.Observable.fromArray("1", "2", "3").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e("======================>", "onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
