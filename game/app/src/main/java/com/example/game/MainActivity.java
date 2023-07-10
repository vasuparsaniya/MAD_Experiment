package com.example.game;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private TextView scoreTextView;
    private Button clickButton;

    private int score = 0;
    private int totalTime = 10; // Total time for the game in seconds
    private int clickCount = 0;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        clickButton = findViewById(R.id.clickButton);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementScore();
            }
        });

        startGame();
    }

    private void startGame() {
        countDownTimer = new CountDownTimer(totalTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                timerTextView.setText("Time: " + seconds);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Time: 0");
                clickButton.setEnabled(false);
                showScore();
            }
        };

        countDownTimer.start();
    }

    private void incrementScore() {
        score++;
        clickCount++;
        scoreTextView.setText("Score: " + score);
    }

    private void showScore() {
        Toast.makeText(this, "Game Over! Your score is " + score + " in " + clickCount + " clicks.", Toast.LENGTH_LONG).show();
    }
}
