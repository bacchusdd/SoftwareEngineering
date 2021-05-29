package com.example.motiondetectionalert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import static android.content.Context.WINDOW_SERVICE;

public class PhotoListAdapter extends BaseAdapter {


    Context context;

    //이미지 배열. 아마도 db에서 가져온 url?
    int photoIds[] = {R.drawable.ex1, R.drawable.ex2, R.drawable.ex3, R.drawable.ex4, R.drawable.ex5};

    public PhotoListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return photoIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        this.context = context;

        ImageView photo = new ImageView(context);

        Display display = ((WindowManager)context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        int x = display.getWidth() / GalleryPhotoList.gridView1.getNumColumns();
        photo.setLayoutParams(new GridView.LayoutParams(x, (int) (x * 1.5)));
        photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        photo.setPadding(5, 5, 5, 5);
        photo.setImageResource(photoIds[position]);

        // 이미지를 클릭하는 경유
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog.xml 인플레이트
                View dialogView = View.inflate(context, R.layout.activity_photo_box, null);

                // Dialog 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ImageView miniphoto = dialogView.findViewById(R.id.miniphoto);

                miniphoto.setImageResource(photoIds[position]);
                builder.setView(dialogView);

                builder.setPositiveButton("닫기", null);

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        return photo;
    }
}