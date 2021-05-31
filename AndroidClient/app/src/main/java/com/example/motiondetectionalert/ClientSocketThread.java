package com.example.motiondetectionalert;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ClientSocketThread extends Thread {

    private String host = "192.168.0.6";
    private int port;

    private Socket socket;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private int inputCode;
    private int outputCode;

    ClientSocketThread(int port, int code){
        this.inputCode = code;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(host, port);
            Log.w("Connection", "Success");
        } catch (IOException e) {
            Log.w("Connection", "Failed");
            e.printStackTrace();
        }

        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int data = inputCode;

            dataOutputStream.writeUTF("From Client : " + data);
            dataOutputStream.flush();

            if(data == 1){
                outputCode = 1;
                Log.w("outputCode ", Integer.toString(outputCode));
                Log.w("From server", "Start Detection " + data);
            }
            else if(data == 2){
                outputCode = 2;
                Log.w("From server", "Stop Detection " + data);
            }
            else
                Log.w("From server", "Unknown Code " + data);

        } catch (Exception e) {
            Log.w("Error", e.getMessage());
        }
        finally {
            try{
                Log.w("Connection", "closed");
                socket.close();
            } catch(Exception e){
            };
        }
    }

    public int getOutputCode(){
        Log.w("OutputCode", Integer.toString(outputCode));
        Log.w("OutputCode", Integer.toString(outputCode));
        return outputCode;
    }

}