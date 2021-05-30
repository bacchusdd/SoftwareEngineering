package com.example.motiondetectionalert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;

import java.util.ArrayList;

import static android.content.Context.WINDOW_SERVICE;

public class PhotoListAdapter extends BaseAdapter {


    Context context;

    //이미지 배열. 아마도 db에서 가져온 url?
    //int photoIds[] = {R.drawable.ex1, R.drawable.ex2, R.drawable.ex3, R.drawable.ex4, R.drawable.ex5};

    private ArrayList<String> mData = new ArrayList<>();
    //private LayoutInflater inflater;


    private PhotoListAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(PhotoListAdapter.OnItemClickListener onItemClickListener) {
        this.mListener = (OnItemClickListener) onItemClickListener;
    }
    public  static interface  OnItemClickListener{
        void onItemClick(View v, int pos) throws JSONException;
    }

    public PhotoListAdapter(ArrayList<String> list) {
        //inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mData = list;
    }

    @Override
    public int getCount() {
        Log.d("test", "listsize = " + mData.size());
        return mData.size();
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
        Log.d("test", "resource = " + mData.get(position));


        Glide.with(context).load(mData.get(position)).into(photo);

        // 이미지를 클릭하는 경유
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // dialog.xml 인플레이트
                View dialogView = View.inflate(context, R.layout.activity_photo_box, null);

                // Dialog 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ImageView miniphoto = dialogView.findViewById(R.id.miniphoto);

                Glide.with(context).load(mData.get(position)).into(miniphoto);

                //miniphoto.setImageResource(mData.indexOf(position));
                builder.setView(dialogView);

                builder.setPositiveButton("닫기", null);

                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });
        return photo;
    }
}
