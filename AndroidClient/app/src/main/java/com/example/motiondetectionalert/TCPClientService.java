package com.example.motiondetectionalert;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClientService extends Service {
    public static final String ACTION = "com.example.motiondetectionalert.TCPClientService";

    private String html = "";
    private Handler handler;

    private Socket socket;

    private BufferedReader networkReader;
    private BufferedWriter networkWriter;

    private String ip = getString(R.string.flask_url);
    private int port = 9999;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { //For communication between Service and Activity
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) { //When service is executed

//        handler.post(new DisplayToast(this, "Toast from IntentService"));

        try {
            setSocket(ip, port);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        checkUpdate.start();

//        PrintWriter out = new PrintWriter(networkWriter, true);
//        String return_msg = et.getText().toString();
//        out.println(return_msg);

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    private Thread checkUpdate = new Thread() {
        public void run() {
            try {
                String line;
                Log.w("Socket Start", "Start Thread");
                while (true) {
                    Log.w("Socket is running", "socket is running");
                    line = networkReader.readLine();
                    html = line;
                    handler.post(showUpdate);
                }
            } catch (Exception e) {

            }
        }
    };

    private Runnable showUpdate = new Runnable() {
        public void run() {
//            Toast.makeText(NewClient.this, "Coming word: " + html, Toast.LENGTH_SHORT).show();
        }
    };

    public void setSocket(String ip, int port) throws IOException {
        try {
            socket = new Socket(ip, port);
            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }


}
