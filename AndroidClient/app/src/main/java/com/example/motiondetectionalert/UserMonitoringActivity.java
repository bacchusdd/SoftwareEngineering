package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserMonitoringActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private PromptAPI promptAPI;
    private Call<PromptResult> promptList;

    private Callback<PromptResult> promptCallback = new Callback<PromptResult>() {
        @Override
        public void onResponse(Call<PromptResult> call, Response<PromptResult> response) {
            PromptResult result = response.body();
//            Intent startMonitoringIntent = new Intent(getApplicationContext(), StartMonitoringActivity.class);
//            startActivity(startMonitoringIntent); //Move Scene
        }

        @Override
        public void onFailure(Call<PromptResult> call, Throwable t) {
            Toast.makeText(UserMonitoringActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            t.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermonitoring);

        // 모니터링 중지 버튼: 모니터링 중지 이벤트 발생용
        Button stopMonitoringButton = findViewById(R.id.stopMonitoringButton);
        stopMonitoringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientSocketThread clientSocketThread = new ClientSocketThread(9998, 1);
                clientSocketThread.start();
                try {
                    clientSocketThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(clientSocketThread.getOutputCode() == 2){
                    Intent startMonitoringIntent = new Intent(getApplicationContext(), StartMonitoringActivity.class);
                    startActivity(startMonitoringIntent); //Move Scene
                }
            }
        });
    }

    private void postStopMonitoringRequest(){
        // Init Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.flask_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        promptAPI = retrofit.create(PromptAPI.class);

        //Post stopMonitoring to server
        promptList = promptAPI.postStopDetection();
        promptList.enqueue(promptCallback);
    }

}
