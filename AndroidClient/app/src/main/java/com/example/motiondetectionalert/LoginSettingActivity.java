package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginSettingActivity extends AppCompatActivity {

    AlertDialog dialog;
    Button Button_backspace, Button_change, Button_deleteaccount;
    EditText EditText_newpw, EditText_newpwcheck;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsetting);

        Button_backspace = findViewById(R.id.Button_backspace);
        Button_change = findViewById(R.id.Button_change);
        Button_deleteaccount = findViewById(R.id.Button_deleteaccount);
        EditText_newpw = findViewById(R.id.EditText_newpw);
        EditText_newpwcheck = findViewById(R.id.EditText_newpwcheck);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String id = bundle.getString("id");
        final String password = bundle.getString("password");

        Button_backspace.setClickable(true);
        Button_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSettingActivity.this,SettingActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });

        Button_change.setClickable(true);
        Button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpw = EditText_newpw.getText().toString();
                String newpwcheck = EditText_newpwcheck.getText().toString();
                if (newpw.equals("") || newpwcheck.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginSettingActivity.this);
                    dialog = builder.setMessage("Please fill all of the fields.").setNegativeButton("ok", null).create();
                    dialog.show();
                    return;
                }

                if (!newpw.equals(newpwcheck)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginSettingActivity.this);
                    dialog = builder.setMessage("Passwords are not same.").setNegativeButton("ok", null).create();
                    dialog.show();
                    return;
                }

                String url = "http://SERVER_IP:5000/changepw?id="+id+"&pw="+newpw;
                new ChangePassword().execute(url);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (result.equals("success")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginSettingActivity.this);
                    dialog = builder.setMessage("Complete to change password.").setNegativeButton("ok", null).create();
                    dialog.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginSettingActivity.this);
                    dialog = builder.setMessage("Failed to change password.").setNegativeButton("ok", null).create();
                    dialog.show();
                    return;
                }

                Intent intent = new Intent(LoginSettingActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button_deleteaccount.setClickable(true);
        Button_deleteaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://SERVER_IP:5000/deleteaccount?id="+id;
                new ChangePassword().execute(url);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (result.equals("success")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginSettingActivity.this);
                    dialog = builder.setMessage("Complete to delete your account.").setNegativeButton("ok", null).create();
                    dialog.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginSettingActivity.this);
                    dialog = builder.setMessage("Failed to delete your account.").setNegativeButton("ok", null).create();
                    dialog.show();
                    return;
                }

                Intent intent = new Intent(LoginSettingActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private class ChangePassword extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            String strUrl = params[0];
            try {
                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();
                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.body().string());
                Boolean isSuccess = jsonObject.getBoolean("isSuccess");
                if (isSuccess) {
                    result = "success";
                }
                else {
                    result = "failure";
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}