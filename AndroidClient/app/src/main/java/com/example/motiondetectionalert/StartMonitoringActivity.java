package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartMonitoringActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private DetectionAPI detectionAPI;
//    private Call<String>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmonitoring);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.166:3306/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Button startMonitoring = (Button)findViewById(R.id.start_monitoring);
        startMonitoring.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startMonitoring();
                Intent clientMonitoringIntent = new Intent(getApplicationContext(), ClientMonitoringActivity.class);
//                postRequest("your message here", url);
                startActivity(clientMonitoringIntent); //Move Scene
            }
        });


    }

    //Ask server to start monitoring
    private void startMonitoring(){
        //Request server to start monitoring

    }

}
