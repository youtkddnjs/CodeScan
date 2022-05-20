package com.example.codescan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    Button gotoscanBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoscanBT = findViewById(R.id.gotoscanBT);

        gotoscanBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(MainActivity.this, ScanView.class);
                startActivity(intent);
            }
        });
        
        
        //권한 요청
        int checkPermissionResult = checkSelfPermission(Manifest.permission.CAMERA);
        if(checkPermissionResult == PackageManager.PERMISSION_DENIED){
            String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissions, 10);
        }

        new HashKey(this).getHashKey();
    }//onCreate

    
    //권한 요청 결과 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case 10:
                if(grantResults[0] == PackageManager.PERMISSION_DENIED || grantResults[1] == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this, "권한 설정 하세요.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "권한 설정 되었음.", Toast.LENGTH_SHORT).show();
                }
                break;

        }//switch (requestCode)

    }//onRequestPermissionsResult


}//MainActivity