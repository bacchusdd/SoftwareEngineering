package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraMonitoringActivity extends AppCompatActivity {
    private CameraSetting cameraSetting;
    private MotionDetector motionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameramonitoring);

        // 프리뷰뷰: 카메라 화면 송출
        PreviewView previewView = findViewById(R.id.viewFinder);

        // 카메라세팅: 카메라 초기화
        // 화면 송출을 지속적으로 하기 위해서 새로운 쓰레드를 생성해서 넣어줌.
        cameraSetting = new CameraSetting(this, previewView, Executors.newSingleThreadExecutor());
        motionDetector = new MotionDetector(cameraSetting); // 모션디텍터 객체

    }

    // 모니터링을 실행하면 카메라 프리뷰를 시작함과 동시에 모션 디텍팅을 시작함.
    @Override
    protected void onResume() {
        super.onResume();
        motionDetector.startMonitoring();
    }

    @Override
    protected void onPause() {
        super.onPause();
        motionDetector.stopMonitoring();
    }

}
