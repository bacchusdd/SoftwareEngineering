package com.example.motiondetectionalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewPhoto extends AppCompatActivity {

    Button back_button;
    ImageView img;
    Button delete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        this.initializeLayout();
        this.setListener();
    }

    public void initializeLayout(){
        back_button = findViewById(R.id.back_gallery);
        img = findViewById(R.id.bigimage);
        delete_button = findViewById(R.id.deleteimg);

        //bringPhoto()
        img.setImageResource(R.drawable.ic_launcher_background);
    }
    public void setListener(){
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(getApplicationContext(), Gallery.class);
                startActivity(gallery);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // delete photo
                // deletePhoto()
                Intent gallery = new Intent(getApplicationContext(), Gallery.class);
                startActivity(gallery);
            }
        });
    }
    //해당 사진 db에서 불러오기. 크게 보여주기
    public void bringPhoto(){

    }

    //해당 사진을 삭제할 것인지
    public void deletePhoto(){

    }

}