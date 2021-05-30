package com.example.motiondetectionalert;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginBlockActivity extends AppCompatActivity {

    private TextView TextView_countdown;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginblock);

        TextView_countdown = findViewById(R.id.TextView_countdown);

        int count = 3600; // 1 h anyway

        handler.post(new CountRunnable(count));
    }

    private class CountRunnable implements Runnable {
        private int count;
        public CountRunnable(int _count) {
            this.count = _count;
        }

        @Override
        public void run() {

            count -= 1;
            TextView_countdown.setText(count+"");

            if (count > 0) {
                handler.postDelayed(this, 1000);
            }
            else {
                Intent intent = new Intent(LoginBlockActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}