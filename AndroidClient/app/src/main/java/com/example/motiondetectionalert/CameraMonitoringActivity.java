package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraMonitoringActivity extends AppCompatActivity {
    private CameraSetting cameraSetting;
    private MotionDetector motionDetector;
    private Notifier notifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameramonitoring);

        // 프리뷰뷰: 카메라 화면 송출
        PreviewView previewView = findViewById(R.id.viewFinder);

        // 모니터링 중지 버튼: 모니터링 중지 이벤트 발생용
        Button stopMonitoringButton = findViewById(R.id.stopMonitoringButton);
        stopMonitoringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motionDetector.stopMonitoring();
                Intent startMonitoringActivityIntent = new Intent(getApplicationContext(), StartMonitoringActivity.class);
                startActivity(startMonitoringActivityIntent);
            }
        });

        // 찍은 사진에 Lock을 걸도록 하는 세마포어
        Semaphore imageOpenLock = new Semaphore(1);
        // 카메라세팅: 카메라 초기화. 화면 송출을 지속적으로 하기 위해서 새로운 쓰레드를 생성해서 넣어줌.
        cameraSetting = new CameraSetting(this, previewView, Executors.newSingleThreadExecutor(), imageOpenLock);
        // 모션디텍터 객체: 찍은 사진을 바탕으로 모션감지
        motionDetector = new MotionDetector(cameraSetting, imageOpenLock);
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
