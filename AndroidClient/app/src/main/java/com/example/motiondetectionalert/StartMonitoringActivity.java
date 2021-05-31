package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartMonitoringActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private PromptAPI promptAPI;
    private Call<PromptResult> promptList;

    private Callback<PromptResult> promptCallback = new Callback<PromptResult>() {
        @Override
        public void onResponse(Call<PromptResult> call, Response<PromptResult> response) {
            if (response.isSuccessful()) {
                PromptResult result = response.body();
                Toast.makeText(StartMonitoringActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                Log.d("TEST", result.toString());

            }
        }

        @Override
        public void onFailure(Call<PromptResult> call, Throwable t) {
            Toast.makeText(StartMonitoringActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            t.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmonitoring);

//        Intent tcpClientService = new Intent(getApplicationContext(), TCPClientService.class);
//        startService(tcpClientService);

        Button startMonitoring = (Button) findViewById(R.id.start_monitoring);
        startMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientSocketThread clientSocketThread = new ClientSocketThread(9999, 1);
                clientSocketThread.start();
                try {
                    clientSocketThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(clientSocketThread.getOutputCode() == 1){
                    Intent userMonitoringIntent = new Intent(getApplicationContext(), UserMonitoringActivity.class);
                    startActivity(userMonitoringIntent); //Move Scene
                }
                //postStartMonitoringRequest();
            }
        });

        Button setAsCamera = (Button) findViewById(R.id.set_as_camera);
        setAsCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraMainIntent = new Intent(getApplicationContext(), CameraMainActivity.class);
                startActivity(cameraMainIntent); //Move Scene
            }
        });
    }

    private void postStartMonitoringRequest() {
        // Init Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.flask_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        promptAPI = retrofit.create(PromptAPI.class);

        Call<PromptResult> call = promptAPI.postStartDetection();

        //Post startMonitoring to server
        promptList = promptAPI.postStartDetection();
        promptList.enqueue(promptCallback);
    }

}