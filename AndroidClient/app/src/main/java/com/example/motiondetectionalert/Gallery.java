package com.example.motiondetectionalert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
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



