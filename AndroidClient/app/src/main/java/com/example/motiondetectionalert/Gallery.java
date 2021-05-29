package com.example.motiondetectionalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity  {

    Button back_button;
    TextView menu;
    ListView date_list;
    List<String> datefromdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        this.initializeLayout();
        this.setListener();
    }

    private void initializeLayout(){
        back_button = (Button)findViewById(R.id.back_button);
        menu = (TextView)findViewById(R.id.text_menu);
        date_list = (ListView)findViewById(R.id.date_list);

        datefromdb = new ArrayList<>();
        datefromdb.add("2021-05-29");
        datefromdb.add("2021-05-30");

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
    }

    //각 날짜 누르면 db에 저장된 photo들 불러오기
    //GalleryPhotoList class 사용
    //adapter 필요
    public void bringPhotoList(){

    }
}