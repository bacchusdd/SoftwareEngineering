package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CameraMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameramain);

        ClientSocketThread clientSocketThread = new ClientSocketThread(9998, 1);
        clientSocketThread.start();
        try {
            clientSocketThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(clientSocketThread.getOutputCode() == 1){
            Intent cameraMonitoringIntent = new Intent(getApplicationContext(), CameraMonitoringActivity.class);
            startActivity(cameraMonitoringIntent); //Move Scene
        }

    }
}
