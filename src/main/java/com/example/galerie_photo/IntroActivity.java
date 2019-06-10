package com.example.galerie_photo;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    TextView txt;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        txt = findViewById(R.id.txt);
        img = findViewById(R.id.img);

        Animation animation = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.introanym);
        img.startAnimation(animation);
        txt.startAnimation(animation);

        Handler handler = new Handler();
        int timeout = 2500;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, timeout);
    }
}
