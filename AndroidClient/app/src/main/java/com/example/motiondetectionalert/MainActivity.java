package com.example.motiondetectionalert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    AlertDialog dialog;
    User u;
    String result;
    EditText EditText_id, EditText_password;
    Button Button_join, Button_login;
    int numOfAttempts = 0;

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

                String url = "http://SERVER_IP:5000/login?id="+id+"&pw="+password;
                new HttpAsyncTask().execute(url);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (result.equals("success")) {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("id",u.getId());
                    intent.putExtra("password",u.getPassword());
                    startActivity(intent);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    dialog = builder.setMessage("Failed to login.").setNegativeButton("ok", null).create();
                    dialog.show();
                    numOfAttempts++;
                    if (numOfAttempts > 5) {
                        numOfAttempts = 0;
                        Intent intent = new Intent(MainActivity.this,LoginBlockActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

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
                String id, pw;
                if (isSuccess) {
                    id = jsonObject.getString("id");
                    pw = jsonObject.getString("password");
                    u = new User(id, pw);
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
