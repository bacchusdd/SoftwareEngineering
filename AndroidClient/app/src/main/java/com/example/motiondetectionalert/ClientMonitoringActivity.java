package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;

import java.util.concurrent.Executors;

public class ClientMonitoringActivity extends AppCompatActivity {
    private CameraSetting cameraSetting;
    private MotionDetector motionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

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

        // 카메라세팅: 카메라 초기화
        // 화면 송출을 지속적으로 하기 위해서 새로운 쓰레드를 생성해서 넣어줌.
        cameraSetting = new CameraSetting(this, previewView, Executors.newSingleThreadExecutor());
        // 모션디텍터 객체
        motionDetector = new MotionDetector(cameraSetting);
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
