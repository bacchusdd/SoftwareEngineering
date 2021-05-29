package com.example.motiondetectionalert;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class SwitchListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<String> date;
    public SwitchListAdapter(Context context, List<String> date){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.date = date;
    }
    @Override
    public int getCount() {
        return date.size();
    }

    @Override
    public Object getItem(int position) {
        return date.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.activity_gallery_date, null);
        Switch switchView = view.findViewById(R.id.switch_date);
        switchView.setText(this.date.get(0));
        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), GalleryPhotoList.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
