package com.example.codescan;



import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ScanView extends AppCompatActivity {

    public Context context;
    SurfaceView surfaceView;
    Handler autofocus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanview);
        setID();
        context = this;

        autofocus = new Handler();



    }//onCreate

    void setID(){
        surfaceView = findViewById(R.id.sv);
    }
}//ScanView
