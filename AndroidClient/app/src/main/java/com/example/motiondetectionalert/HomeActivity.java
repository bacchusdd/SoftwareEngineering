package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView TextView_device;
    Button Button_start, Button_gallery, Button_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView_device = findViewById(R.id.TextView_device);
        Button_start = findViewById(R.id.Button_start);
        Button_gallery = findViewById(R.id.Button_gallery);
        Button_setting = findViewById(R.id.Button_setting);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String id = bundle.getString("id");
        final String password = bundle.getString("password");
        TextView_device.setText("User Id : "+id);

        Button_setting.setClickable(true);
        Button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SettingActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });
    }
}