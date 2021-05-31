package com.example.motiondetectionalert;

import android.net.Uri;
import android.util.Log;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

class Result {
    String msg;
}

interface AlertAPI {
    @Multipart
    @POST("/alert")
    Call<Result> uploadImage(@Part MultipartBody.Part file);
}


public class Notifier {
    private final CameraSetting cameraSetting;
    private Retrofit retrofit;
    private AlertAPI alertAPI;

    public Notifier(CameraSetting cameraSetting) {
        this.cameraSetting = cameraSetting;
        InitRetrofit();
    }

    public void sendAlert() {
        Uri imageUri = cameraSetting.getTakenUri();
        File imageFile = new File(imageUri.getPath());

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img", imageFile.getName(), requestFile);

        Call<Result> resultCall = alertAPI.uploadImage(body);
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("TEST", "POST 성공??");
                if (response.isSuccessful()) {
                    Log.d("TEST", "POST 성공 성공");
                    Log.d("TEST", response.body().msg);
                    // 전송하고 나면 전송했던 사진을 지운다.
                    // 모션 감지하면서 계속 사진 파일이 쌓이면 안되니까..
                    cameraSetting.deleteTakenImage(cameraSetting.getTakenUri());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("TEST", "POST 실패..");
                cameraSetting.deleteTakenImage(cameraSetting.getTakenUri());
            }
        });
    }

    private void InitRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.10:3306/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        alertAPI = retrofit.create(AlertAPI.class);
    }
}
