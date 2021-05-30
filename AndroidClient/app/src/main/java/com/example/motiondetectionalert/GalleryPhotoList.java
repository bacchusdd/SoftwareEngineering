package com.example.motiondetectionalert;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GalleryPhotoList extends AppCompatActivity {
    ImageButton back_btn;
    static GridView gridView1;
    PhotoListAdapter adapter;
    private ArrayList<String> photolist;
    private String user_id;
    private String date;
    private TextView textView;
    private String TAG;
    //private static final String url = "http://192.168.0.166:3306/";
    ArrayList<String> list = new ArrayList<>();
    int count;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo_list);

        Intent intent = getIntent();
        user_id = "kang";
        date = intent.getStringExtra("date");

        System.out.println("user_id & date : " + user_id + "/" + date);


        String url = "http://10.0.2.2:5000/photolist?user_id=" + user_id + "&date=" + date;
        System.out.println(url);

        new sendData().execute(url);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println(result);

        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gridView1 = findViewById(R.id.gridView1);
        adapter = new PhotoListAdapter(list);
        gridView1.setAdapter(adapter);


        int counting = list.size();
        Log.d("test", "listsize = " + counting);


        /*
        for (int j = 0; j < counting; j++){
            System.out.println("url이다" + list.get(j-1));
        }
       */



        //TimeUnit.SECONDS.sleep(1);
        //StringTokenizer str = new StringTokenizer(result);
        //count = str.countTokens();
        //while (str.hasMoreTokens()) {
        //    list.add(str.nextToken());
        //    System.out.println("url이다" + list.get(j));
        //    j++;
        //}

        /*
        for (int i = 0; i < count; i++){
            list.set(i, String.valueOf(count));
            System.out.println("url이다" + list.get(i));
        }
        */

        /*
        photolist = RetrofitCon.getInstance().getService().getPhotolist(user_id, date);

        photolist.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                int status = response.code();
                Log.d(TAG, "code: " + status);
                textView.setText(response.body().toString());
                List<JsonObject> list = response.body();

                Log.d(TAG, "리스트 사이즈: " + list.size());

                Log.d(TAG, "0번째 id: " + list.get(0).get("id"));
                System.out.println(list.get(0).get("urls"));
            }
            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                Log.d(TAG, "실패");
            }
        });



         */


    }

    private class sendData extends AsyncTask<String, Void, ArrayList<String>> {

        OkHttpClient client = new OkHttpClient();



        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            try {
                /*
                OkHttpClient client = new OkHttpClient();
                JSONObject jsonInput = new JSONObject();
                jsonInput.put("user_id", user_id);
                jsonInput.put("date", date);
                Log.i("test", jsonInput.toString());

                RequestBody reqBody = RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        jsonInput.toString()
                );
                */
                Request request = new Request.Builder()
                        .url(params[0])
                        .build();

                Response responses = client.newCall(request).execute();

                try {
                    responses = client.newCall(request).execute();
                    //JSONObject jsonObject = new JSONObject(responses.body().string());
                    JSONArray output = new JSONArray(responses.body().string());
                    Log.d("test", "json_array.get(0) = " + output.get(0));
                    Log.d("test", "json_array.get(1) = " + output.get(1));
                    Log.d("test", "string = " + output.get(1).toString());
                    //System.out.println("url : " + jsonObject);
                    if (output != null && output.length() > 0){
                        for (int i = 0; i < output.length(); i++){
                            list.add(output.get(i).toString());
                            Log.d("test", "list = " +list.get(i));
                            Log.d("test","i" + i);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //Boolean isSuccess = jsonObject.getBoolean("isSuccess");

                //message = jsonObject.getString("ph");
                //System.out.println("message" + message);

                //result = message.equals()

                //SONArray a = output.get



            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}
