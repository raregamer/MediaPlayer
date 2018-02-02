package com.example.samue.mediaplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
//Media player test project
public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private Button pauseButton;
    private SeekBar seekbar;
    private int startTime;
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //media player creation
         mediaPlayer = MediaPlayer.create(this,R.raw.song1);

        //play button assignment with OnClickListener.
        playButton = (Button) findViewById(R.id.play_button);

         playButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mediaPlayer.start();
             }
         });

        //pause button assignment with OnClickListener.

        pauseButton = (Button) findViewById(R.id.pause_button);

         pauseButton.setOnClickListener(new View.OnClickListener() {
              @Override
             public void onClick(View view){

                mediaPlayer.pause();
         }

         });
            //Seekbar assignment
         seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setClickable(false);
        seekbar.setMax(mediaPlayer.getDuration());
        myHandler.postDelayed(UpdateSongTime,100);



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                  if(b) {
                      mediaPlayer.seekTo(i);
                  }
              }

              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {

              }

              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {

              }
          });

    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            seekbar.setProgress(mediaPlayer.getCurrentPosition());
            myHandler.postDelayed(this,100);


        }
    };



}
