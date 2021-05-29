package com.example.motiondetectionalert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;

public class GalleryPhotoList extends AppCompatActivity {
    ImageButton back_btn;
    static GridView gridView1;
    PhotoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo_list);

        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gridView1 = findViewById(R.id.gridView1);
        adapter = new PhotoListAdapter(this);

        gridView1.setAdapter(adapter);

    }



}