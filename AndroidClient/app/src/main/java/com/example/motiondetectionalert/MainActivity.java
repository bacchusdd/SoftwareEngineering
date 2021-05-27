package com.example.motiondetectionalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText EditText_id, EditText_password;
    Button Button_join, Button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText_id = findViewById(R.id.EditText_id);
        EditText_password = findViewById(R.id.EditText_password);
        Button_join = findViewById(R.id.Button_join);
        Button_login = findViewById(R.id.Button_login);

        Button_join.setClickable(true);
        Button_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button_login.setClickable(true);
        Button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = EditText_id.getText().toString();
                String password = EditText_password.getText().toString();

                // send id,password to server, and get answer
                // if answer if true, move to HomeActivity
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });
    }
}
