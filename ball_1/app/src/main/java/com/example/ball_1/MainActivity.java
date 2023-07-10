package com.example.ball_1;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ballImageView;
    private int currentRadius;
    private int currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ballImageView = findViewById(R.id.ball_image_view);
        currentRadius = 2;
        currentColor = Color.RED;

        rotateBallInCircle();
        changeSizeAndColorAfterDelay();
    }

    private void rotateBallInCircle() {
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(ballImageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(60000); // 1 minute
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        rotateAnimator.start();
    }

    private void changeSizeAndColorAfterDelay() {
        ballImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentRadius == 2) {
                    currentRadius = 4;
                    currentColor = Color.BLUE;
                } else if (currentRadius == 4) {
                    currentRadius = 6;
                    currentColor = Color.GREEN;
                }

                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(ballImageView, "scaleX", currentRadius / 2f);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(ballImageView, "scaleY", currentRadius / 2f);
                animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
                animatorSet.setDuration(500); // 0.5 seconds
                animatorSet.start();

                ballImageView.setColorFilter(currentColor);

                ballImageView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeSizeAndColorAfterDelay();
                    }
                }, 3000); // 3 seconds
            }
        }, 60000); // 1 minute
    }
}
