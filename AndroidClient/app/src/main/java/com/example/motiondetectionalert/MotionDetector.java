package com.example.motiondetectionalert;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MotionDetector {
    private final double differenceThreshold = 5;
    private final CameraSetting cameraSetting;

    private Timer imageCaptureTimer;
    private Timer endMonitoringTimer;
    private boolean isMonitoring;

    public MotionDetector(CameraSetting camerasetting) {
        cameraSetting = camerasetting;
        isMonitoring = false;
    }

    public void startMonitoring() {
        Log.d("startMonitoring", "monitoring start");
        startTimer();
        isMonitoring = true;
    }

    // stopPreview()가 main Thread에서만 동작함.
    // stopPreview()는 다른 쓰레드에서 호출되면 에러를 발생시킴.
    public void stopMonitoring() {
        Log.d("stopMonitoring", "monitoring stop");
        cameraSetting.stopCamera();
//        cameraSetting.stopPreview();
        cameraSetting.setPrevImageNull();
        cameraSetting.setTakenImageNull();
        stopTimer();
        isMonitoring = false;
    }

    public void takePhoto() {
        if (isMonitoring)
            cameraSetting.takePhoto();
    }

    private void startTimer() {
        imageCaptureTimer = new Timer();
        TimerTask imageCaptureTimerTask = new TimerTask() {
            @Override
            public void run() {
                takePhoto();
                detectMotion(cameraSetting.getTakenImage(), cameraSetting.getPrevImage());
            }
        };

        endMonitoringTimer = new Timer();
        TimerTask endMonitoringTimerTask = new TimerTask() {
            @Override
            public void run() {
                stopMonitoring();
            }
        };

        imageCaptureTimer.schedule(imageCaptureTimerTask, 1500, 1500);
        endMonitoringTimer.schedule(endMonitoringTimerTask, 1000 * 3600);
    }

    private void stopTimer() {
        imageCaptureTimer.cancel();
        endMonitoringTimer.cancel();
    }

    private void detectMotion(Bitmap bitmap1, Bitmap bitmap2) {
        if (bitmap1 == null || bitmap2 == null || !isMonitoring)
            return;

        Bitmap resizedBitmap1 = Bitmap.createScaledBitmap(bitmap1, 16, 12, true);
        Bitmap resizedBitmap2 = Bitmap.createScaledBitmap(bitmap2, 16, 12, true);
        double difference = 0;

        Log.d("detectMotion", "detecting motion..");

        try {
            difference = getDifference(resizedBitmap1, resizedBitmap2);
        } catch (IllegalArgumentException e) {
            Log.d("detectMotion", e.getMessage());
        }
        // 만약 차이가 differenceThreshold 보다 크면 움직임 감지
        if (difference > differenceThreshold) {
            // 움직임 감지 이벤트
            stopMonitoring();
            Log.d("detectMotion", "motion detected!");
        }
    }

    private double getDifference(Bitmap img1, Bitmap img2) throws IllegalArgumentException {
        // 이미지 사이즈가 서로 맞지 않을 경우 예외 반환
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            String errMsg = String.format(Locale.KOREA, "(%d, %d) vs (%d, %d)", img1.getWidth(), img1.getHeight(), img2.getWidth(), img2.getHeight());
            throw new IllegalArgumentException("Images must have the same dimesions: " + errMsg);
        }

        long diff = 0L;
        long maxDiff = 3L * 255 * img1.getWidth() * img1.getWidth();

        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                diff += pixelDiff(img1.getPixel(x, y), img2.getPixel(x, y));
            }
        }
        return 100.0 * diff / maxDiff;
    }

    private int pixelDiff(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >> 8) & 0xff;
        int b1 = rgb1 & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >> 8) & 0xff;
        int b2 = rgb2 & 0xff;
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1- b2);
    }
}
