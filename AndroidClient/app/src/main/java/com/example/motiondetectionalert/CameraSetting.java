package com.example.motiondetectionalert;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class CameraSetting {
    private final String TAG = "CameraXBasic";
    private final String outputDirectory;

    private final ExecutorService cameraExecutor;
    private final Context mainContext;
    private final PreviewView viewFinder;

    private ImageCapture imageCapture = null;
    private ProcessCameraProvider cameraProvider;

    private Bitmap takenImageBitmap = null;
    private Bitmap prevtakenImageBitmap = null;

    public CameraSetting(Context context, PreviewView previewView, ExecutorService executorService) {
        mainContext = context;
        viewFinder = previewView;
        cameraExecutor = executorService;
        outputDirectory = mainContext.getExternalFilesDir(null).toString();
    }

    public void startCamera() {
        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(mainContext);

        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    // 카메라 라이프사이클 바인드하는데 쓰인다.
                    cameraProvider = cameraProviderFuture.get();

                    // 카메라의 프리뷰를 보여주는 객체
                    Preview preview = new Preview.Builder().build();
                    preview.setSurfaceProvider(viewFinder.getSurfaceProvider());

                    // 이미지 캡쳐용 객체
                    imageCapture = new ImageCapture.Builder().build();

                    // 후면 카메라를 사용할꺼야
                    CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                    cameraProvider.unbindAll();
                    cameraProvider.bindToLifecycle((LifecycleOwner)mainContext, cameraSelector, preview, imageCapture);

                } catch (ExecutionException | InterruptedException e) {
                    // No errors need to be handled for this Future.
                    // This should never be reached.
                }
            }
        }, ContextCompat.getMainExecutor(mainContext));
    }

    public void takePhoto() {
        if (imageCapture == null)
            return;

        // 타임스탬프 출력파일 만들기
        String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
        final File photoFile = new File(outputDirectory, new SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis()) + ".png");
        // 파일과 함께 저장될 메타데이터가 들어갈 옵션 객체 생성
        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(photoFile).build();

        // 이미지 캡쳐 리스너 등록
        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(mainContext), new ImageCapture.OnImageSavedCallback() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                Uri savedUri = Uri.fromFile(photoFile);

                // 이전에 찍었던 사진을 옮기고
                prevtakenImageBitmap = takenImageBitmap;
                // 새로 찍은 사진을 저장
                takenImageBitmap = getImageBitmapFromUri(savedUri);
                // 찍은 사진 제거
                deleteTakenImage(savedUri);

                String msg = "Photo capture succeeded: " + savedUri.toString();
//                Toast.makeText(mainContext, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, msg);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Log.d(TAG, "Photo captured failed: " + exception.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private Bitmap getImageBitmapFromUri(Uri uri) {
        Bitmap bitmap = null;
        try {
             bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(mainContext.getContentResolver(), uri)).copy(Bitmap.Config.RGBA_F16, true);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("getImageFromUri", "exception");
        }
        return bitmap;
    }

    private void deleteTakenImage(Uri uri) {
        File file = new File(uri.getPath());
        if(file.exists()) {
            boolean isDelete = file.delete();
            if(isDelete) Log.e("file delete ?", String.valueOf(isDelete));
        }
    }

    public void stopCamera() {
        cameraExecutor.shutdown();
    }

    public void stopPreview() {
        cameraProvider.unbindAll();
    }

    public Bitmap getTakenImage() { return takenImageBitmap; }

    public Bitmap getPrevImage() { return prevtakenImageBitmap; }

    public void setTakenImageNull() { takenImageBitmap = null; }

    public void setPrevImageNull() { prevtakenImageBitmap = null; }
}
