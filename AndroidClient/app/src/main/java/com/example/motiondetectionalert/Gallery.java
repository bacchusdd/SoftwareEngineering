package com.example.motiondetectionalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class Gallery extends AppCompatActivity  {

    Button back_button;
    TextView menu;
    ListView date_list;
    List<String> datefromdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }
        this.initializeLayout();
        this.setListener();
    }

    private void initializeLayout(){
        back_button = (Button)findViewById(R.id.back_button);
        menu = (TextView)findViewById(R.id.text_menu);
        date_list = (ListView)findViewById(R.id.date_list);

        datefromdb = new ArrayList<>();
        bringDates();

        SwitchListAdapter date_adapter = new SwitchListAdapter(this, datefromdb);

        date_list.setAdapter(date_adapter);
    }
    private void setListener(){
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });
    }
    //게시판 형식으로 db에 저장된 date들 불러오기
    //GalleryDate class 사용
    //adapter 필요함
    public void bringDates(){
        String userid = "kang";
        while(true) {
            try {
                URL server = new URL("http://10.0.2.2:5000/history/" + userid);
                HttpURLConnection httpconnection = (HttpURLConnection) server.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(httpconnection.getInputStream()));
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(in);
                JSONObject outputJsonObj = new JSONObject();
                JSONArray jarray = (JSONArray) jsonObj.get("date");

                for (int i = 0; i <jarray.size(); i++){
                    datefromdb.add((String)jarray.get(i));
                }
                break;
            }catch(Exception e){
                System.out.println(e);
                continue;
            }
        }
    }

    //각 날짜 누르면 db에 저장된 photo들 불러오기
    //GalleryPhotoList class 사용
    //adapter 필요

}