package com.example.motiondetectionalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    User u;
    String result;
    EditText EditText_id, EditText_password;
    Button Button_join, Button_login;
    int numOfAttemps = 0;

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

                String url = "http://XXXXXXXX:5000/login?id="+id+"&pw="+password;
                new HttpAsyncTask().execute(url);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (result == "success") {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("id",u.getId());
                    intent.putExtra("password",u.getPassword());
                    intent.putExtra("name",u.getName());
                    intent.putExtra("email",u.getEmail());
                    startActivity(intent);
                }
                else {
                    numOfAttemps++;
                    if (numOfAttemps > 5) {
                        numOfAttemps = 0;
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
                String id, pw, name, email;
                if (isSuccess) {
                    id = jsonObject.getString("id");
                    pw = jsonObject.getString("password");
                    name = jsonObject.getString("name");
                    email = jsonObject.getString("email");
                    u = new User(id, pw, name, email);
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

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                Log.d("Network", s);
            }
        }
    }
}
