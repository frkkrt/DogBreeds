package com.furkankurt.dogbreeds.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.furkankurt.dogbreeds.R;
import com.furkankurt.dogbreeds.Activity.MainActivity;


public class SplashScreen extends Fragment {
    private ProgressBar progressBar;
    private int mProgressBarStatus=0;
    private Handler handler=new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        progressBar=view.findViewById(R.id.progressbar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                    while(mProgressBarStatus<100)
                    {
                            mProgressBarStatus++;
                            SystemClock.sleep(50);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(mProgressBarStatus);
                                }
                            });

                    }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences ayarlar = getActivity().getSharedPreferences("Splash", MODE_PRIVATE);
                        SharedPreferences.Editor editor4= ayarlar.edit();
                        editor4.putInt("first", 4);
                        editor4.commit();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);


                    }
                });
            }
        }).start();


        return view;
    }

}