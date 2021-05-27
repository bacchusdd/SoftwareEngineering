package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    Button Button_backspace, Button_login_setting, Button_alert_setting;
    Button Button_camera_setting, Button_timer_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button_backspace = findViewById(R.id.Button_backspace);
        Button_login_setting = findViewById(R.id.Button_login_setting);
        Button_alert_setting = findViewById(R.id.Button_alert_setting);
        Button_camera_setting = findViewById(R.id.Button_camera_setting);
        Button_timer_setting = findViewById(R.id.Button_timer_setting);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String id = bundle.getString("id");
        final String password = bundle.getString("password");

        Button_backspace.setClickable(true);
        Button_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,HomeActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });

        Button_login_setting.setClickable(true);
        Button_login_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,LoginSettingActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });
    }
}
