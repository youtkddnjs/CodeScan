package com.example.codescan;



import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ScanView extends AppCompatActivity {

    final int FOCUS_START = 0;

    public Context context;
    SurfaceView surfaceView;
    ScanSurfaceView scanSurfaceView;
    Handler autofocus;

    ImageView captureBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanview);
        setID();
        context = this;

        autofocus = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case FOCUS_START:
                        break;
                }//switch
            }//handleMessage
        };//autofocus

        
        //프리뷰 보여지는 화면 데이터 받아오기
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scanSurfaceView!=null) {
                    if(scanSurfaceView.camera != null) {
                        PrintLog.PrintVerbose("click");
                        try {
                            scanSurfaceView.camera.setPreviewCallback(previewCallback);
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
            }
        });

    }//onCreate

    //프리뷰 콜백 메소드
    Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] bytes, Camera camera) {
            PrintLog.PrintVerbose("onPreviewFram : " + bytes);
        }//onPreviewFrame
    };//previewCallback

    void setID(){
        surfaceView = findViewById(R.id.sv);
        captureBtn = findViewById(R.id.camerabtn);
    }
}//ScanView
