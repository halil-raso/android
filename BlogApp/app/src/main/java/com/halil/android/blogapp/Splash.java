package com.halil.android.blogapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.LoginTheme);
        setContentView(R.layout.activity_splash);
        tv= (TextView) findViewById(R.id.tv);
        iv = (ImageView)findViewById(R.id.iv);
        Animation myAnimation = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(myAnimation);
        iv.startAnimation(myAnimation);
        final Intent intent = new Intent(this,LoginActivity.class);
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                 startActivity(intent);
                 finish();
                }
            }
        });
        timer.start();

    }
}
