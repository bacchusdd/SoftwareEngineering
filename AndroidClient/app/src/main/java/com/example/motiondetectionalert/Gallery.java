package com.example.motiondetectionalert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Gallery extends AppCompatActivity {
    private ImageButton back_btn;
    private TextView albumtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //back버튼 누르면 이전 페이지로 돌아감
        back_btn = (ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //album title
        albumtext = (TextView) findViewById(R.id.albumtext);
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



