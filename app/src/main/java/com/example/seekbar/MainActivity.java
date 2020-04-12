package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random mRandom;
    SeekBar seekBar;
    Button button;
    int progress, randomNumber, score = 0;
    TextView score2, progress2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRandom = new Random();
        seekBar = findViewById(R.id.seekbar);
        button = findViewById(R.id.play_button);
        score2 = findViewById(R.id.score);
        progress2 = findViewById(R.id.number);
        randomNumber = getRandomNumber();
        progress = seekBar.getProgress();
        setProgress();
    }

    private void setProgress() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress2.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setScore(int points) {
        score +=points;
        score2.setText("Score: " + score);
    }
    private Integer getRandomNumber() {
        return mRandom.nextInt(100) + 1;
    }

    public void start_game (View view) {
        startGame();
    }

    private void startGame() {
        randomNumber = getRandomNumber();
        progress = seekBar.getProgress();
        int difference = Math.abs(progress - randomNumber);
        if (difference == 0){
            setScore(100);
            Toast.makeText(this,"Великолепно " + randomNumber,Toast.LENGTH_LONG).show() ;
        }else if (difference < 20){
            setScore(60);
            Toast.makeText(this,"Отлично " + randomNumber,Toast.LENGTH_LONG).show();
        }else if (difference > 20 && difference < 30 ){
            setScore(40);
            Toast.makeText(this,"Почти "+ randomNumber,Toast.LENGTH_LONG).show();
        }else if (difference > 30){
            setScore(-20);
            Toast.makeText(this,"Не угадал " + randomNumber,Toast.LENGTH_LONG).show();
        }
    }
}
