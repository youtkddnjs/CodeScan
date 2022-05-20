package com.example.codescan;

import android.app.Activity;
import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScanSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    Context m_context;
    Camera camera;
    SurfaceHolder surfaceHolder;
    Camera.Size previewSize;
    SurfaceView localSurfaceView;

    public ScanSurfaceView(Context context) {
        super(context);
        m_context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    public ScanSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }//ScanSurfaceView()

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        try {
            camera = Camera.open(0); //카메라 렌즈 열기
            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceHolder); //미리보기 설정
//            setCameraDisplayOrientation(((ScanView) m_context), camera);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//surfaceCreated

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> sizes_pic = parameters.getSupportedPictureSizes();
        Camera.Size cs = sizes.get(0);
        int mWidth = cs.width;
        int meHeight = cs.height;
        parameters.setPreviewSize(mWidth,meHeight);
        parameters.setPictureSize(sizes_pic.get(0).width,sizes_pic.get(0).height);
        parameters.setJpegQuality(85);
        parameters.setFocusMode(String.valueOf(ImageFormat.JPEG));
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            camera.autoFocus(null);
        } catch (IOException e) {
            e.printStackTrace();
            camera.stopPreview();
            camera.release();
            camera = null;
        }

    }//surfaceChanged

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        camera.release();
        camera.stopPreview();

    }//surfaceDestroyed




    //프리뷰 회전 관련 메소드 (퍼옴)
    public static void setCameraDisplayOrientation(Activity activity, Camera camera) {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                break;
            }
        }

        Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {
            // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }//setCameraDisplayOrientation


}//ScanSurfaceView
