package com.example.motiondetectionalert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import org.json.JSONException;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.WINDOW_SERVICE;

public class PhotoListAdapter extends BaseAdapter {


    Context context;
    String date;
    LayoutInflater layoutInflater;

    //이미지 배열. 아마도 db에서 가져온 url?
    //int photoIds[] = {R.drawable.ex1, R.drawable.ex2, R.drawable.ex3, R.drawable.ex4, R.drawable.ex5};

    HashMap<String, String> list;
    //private LayoutInflater inflater;


    private PhotoListAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(PhotoListAdapter.OnItemClickListener onItemClickListener) {
        this.mListener = (OnItemClickListener) onItemClickListener;
    }
    public  static interface  OnItemClickListener{
        void onItemClick(View v, int pos) throws JSONException;
    }

    public PhotoListAdapter(HashMap<String, String> list, String date) {
        //inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.date = date;
    }

    @Override
    public int getCount() {
        Log.d("test", "listsize = " + list.size());
        return list.size();
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
        this.context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        ImageView photo = new ImageView(context);

        //Display display = ((WindowManager)context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        //int x = display.getWidth() / GalleryPhotoList.gridView1.getNumColumns();
        //photo.setLayoutParams(new GridView.LayoutParams(x, (int) (x * 1.5)));
        //photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //photo.setPadding(5, 5, 5, 5);
        //photo.setImageResource(mData.indexOf(position));
       // Log.d("test", "resource = " + mData.get(position));

        Glide.with(context).load(list.values().toArray()[position]).into(photo);

        // 이미지를 클릭하는 경유
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // dialog.xml 인플레이트
                View dialogView = View.inflate(context, R.layout.activity_photo_box, null);

                // Dialog 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ImageView miniphoto = dialogView.findViewById(R.id.miniphoto);

                Glide.with(context).load(list.values().toArray()[position]).into(miniphoto);

                //miniphoto.setImageResource(mData.indexOf(position));
                builder.setView(dialogView);

                builder.setPositiveButton("닫기", null);
                builder.setNegativeButton("삭제", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // parameter photo_id
                        deletephoto((String) list.keySet().toArray()[position]);
                        Intent intent = new Intent(context.getApplicationContext(), GalleryPhotoList.class);
                        intent.putExtra("date",date);
                        context.startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });
        return photo;
    }

    public void deletephoto(String photo_id){
        while(true) {
            try {
                String userid = "kang";
                URL server = new URL(String.format("http://10.0.2.2:5000/delete/%s/%s/%s",userid, this.date, photo_id));
                HttpURLConnection httpconnection = (HttpURLConnection) server.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(httpconnection.getInputStream()));
                break;
            }catch(Exception e){
                System.out.println(e);
                continue;
            }
        }
    }
}
