package com.example.musicapp.presenter.listenpresenter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.example.musicapp.R;
import com.example.musicapp.model.Song;
import com.example.musicapp.databinding.ActivityListenBinding;

public class ListenActivity extends AppCompatActivity implements ListenInterface {
    ActivityListenBinding binding;
    ListenPresenter listenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listenPresenter = new ListenPresenter(this);
        eventClick();
        listenPresenter.listenMusicBeLike(getIntent(),this);
    }
    @Override
    public void seek(int progress) {
        binding.process.setProgress(progress);
    }

    @Override
    public void play() {
        binding.play.setImageResource(R.drawable.pause);
    }

    @Override
    public void pause() {
        binding.play.setImageResource(R.drawable.play);
    }

    @Override
    public void isReplay() {
        binding.replay.setImageResource(R.drawable.isreplay);
    }

    @Override
    public void notReplay() {
        binding.replay.setImageResource(R.drawable.replay);
    }

    @Override
    public void timeLine(String elapsedTime,int current) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.timeStart.setText(elapsedTime);
                binding.process.setProgress(current);
                if(binding.timeStart.getText().equals(binding.timeFinish.getText())){
                    binding.play.setImageResource(R.drawable.play);
                    stopRotate();
                    if(listenPresenter.isReplay){
                        binding.play.setImageResource(R.drawable.pause);
                        listenPresenter.startRotate();
                    }
                }
            }
        });
    }

    public void eventClick(){
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenPresenter.controlMusic();
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenPresenter.onBackPressed();
                finish();
            }
        });
        binding.replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenPresenter.checkReplay();
            }
        });
        binding.process.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                listenPresenter.seek(progress,fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        listenPresenter.onBackPressed();
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        listenPresenter.onBackPressed();
        super.onDestroy();
    }

    @Override
    public void listenMusicBeLike(Song song) {
        Glide.with(this).load(song.getRotate()).into(binding.rotate);
        binding.nameSong.setText(song.getNameSong());
        binding.author.setText(song.getAuthor());
    }

    @Override
    public void setUpMusic(MediaPlayer mediaPlayer) {
        binding.play.setImageResource(R.drawable.pause);
        binding.process.setMax(mediaPlayer.getDuration());
        String duration = listenPresenter.milliSecondsToString(mediaPlayer.getDuration());
        binding.timeFinish.setText(duration);

    }

    @Override
    public void startRotate(Runnable runnable) {
        binding.rotate.animate().rotationBy(360).withEndAction(runnable).setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();
    }
    @Override
    public void stopRotate() {
        binding.rotate.animate().cancel();
    }
}