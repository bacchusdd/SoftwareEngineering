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

public class RegisterActivity extends AppCompatActivity {

    User u;
    EditText EditText_newid, EditText_newpw, EditText_pwcheck;
    Button Button_back2main, Button_idcheck, Button_register;
    AlertDialog dialog;
    boolean validate = false;
    boolean isIdAlreadyTaken, isSuccess; // boolean (o) Boolean (x)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText_newid = findViewById(R.id.EditText_newid);
        EditText_newpw = findViewById(R.id.EditText_newpw);
        EditText_pwcheck = findViewById(R.id.EditText_pwcheck);
        Button_back2main = findViewById(R.id.Button_back2main);
        Button_idcheck = findViewById(R.id.Button_idcheck);
        Button_register = findViewById(R.id.Button_register);

        Button_back2main.setClickable(true);
        Button_back2main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button_idcheck.setClickable(true);
        Button_idcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newid = EditText_newid.getText().toString();

                if (validate) {
                    return;
                }

                if (newid.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("please enter the ID.").setPositiveButton("ok", null).create();
                    dialog.show();
                    return;
                }

                String url = "http://SERVER_IP:5000/registercheck?id=" + newid;
                new SearchForNewID().execute(url);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!isIdAlreadyTaken) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("This ID is available.").setPositiveButton("use", null).create();
                    dialog.show();

                    EditText_newid.setEnabled(false);
                    validate = true;

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("This ID is already being used.").setPositiveButton("ok", null).create();
                    dialog.show();
                }

            }
        });

        Button_register.setClickable(true);
        Button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newid = EditText_newid.getText().toString();
                String newpw = EditText_newpw.getText().toString();
                String pwcheck = EditText_pwcheck.getText().toString();

                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("Please check if ID is not available.").setNegativeButton("ok", null).create();
                    dialog.show();
                    return;
                }

                if (newid.equals("") || newpw.equals("") || pwcheck.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("Please fill all of the fields.").setNegativeButton("ok", null).create();
                    dialog.show();
                    return;
                }

                if (!(newpw.equals(pwcheck))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("Passwords are not same.").setNegativeButton("ok", null).create();
                    dialog.show();
                    return;
                }

                String url = "http://SERVER_IP:5000/register?id=" + newid + "&pw=" + newpw;
                new RegisterNewIdAndPw().execute(url);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isSuccess) {
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    intent.putExtra("id",u.getId());
                    intent.putExtra("password",u.getPassword());
                    startActivity(intent);
                }
            }
        });

    }

    private class SearchForNewID extends AsyncTask<String, Void, Boolean> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Boolean doInBackground(String... params) {
            String strUrl = params[0];
            try {
                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();
                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.body().string());
                Integer status = jsonObject.getInt("status");

                if (status == 400) { // status 400 : is already taken, 200 : no id in DB
                    isIdAlreadyTaken = true;
                } else { // status == 200
                    isIdAlreadyTaken = false;
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return isIdAlreadyTaken;

            // onPostExecute() ?
        }
    }

    private class RegisterNewIdAndPw extends AsyncTask<String, Void, Boolean> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Boolean doInBackground(String... params) {
            String strUrl = params[0];
            try {
                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();
                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.body().string());
                Integer status = jsonObject.getInt("status");

                String id, pw;
                if (status == 200) { // status 400 : fail, 200 : success
                    id = jsonObject.getString("id");
                    pw = jsonObject.getString("password");
                    u = new User(id, pw);
                    isSuccess = true;
                } else { // status == 400
                    isSuccess = false;
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return isSuccess;
        }
    }
}