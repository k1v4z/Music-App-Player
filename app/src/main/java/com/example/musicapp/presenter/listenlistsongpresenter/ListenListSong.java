package com.example.musicapp.presenter.listenlistsongpresenter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.example.musicapp.R;
import com.example.musicapp.databinding.ActivityListenListSongBinding;
import com.example.musicapp.model.Song;

public class ListenListSong extends AppCompatActivity implements ListenListInterface {
    ActivityListenListSongBinding binding;
    ListenListPresenter listenListPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListenListSongBinding.inflate(getLayoutInflater());
        listenListPresenter = new ListenListPresenter(this);
        listenListPresenter.listenListMusic(getIntent(),this);
        eventClick();
        setContentView(binding.getRoot());
    }
    public void eventClick(){
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenListPresenter.controlMusic();
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenListPresenter.onBackPressed();
                finish();
            }
        });
        binding.replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenListPresenter.checkReplay();
            }
        });
        binding.process.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                listenListPresenter.seek(progress,fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenListPresenter.nextSong();
            }
        });
        binding.previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenListPresenter.previousSong();
            }
        });
        binding.shuffling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenListPresenter.checkShuffling();
            }
        });
    }

    @Override
    public void setUpSong(Song song) {
        Glide.with(this).load(song.getRotate()).into(binding.rotate);
        binding.nameSong.setText(song.getNameSong());
        binding.author.setText(song.getAuthor());
    }

    @Override
    public void setUpMusic(MediaPlayer musicPlayer) {
        binding.play.setImageResource(R.drawable.pause);
        binding.process.setMax(musicPlayer.getDuration());
        String duration = listenListPresenter.milliSecondsToString(musicPlayer.getDuration());
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
    public void isShuffling() {
        binding.shuffling.setImageResource(R.drawable.is_shuffling);
    }

    @Override
    public void notShuffling() {
        binding.shuffling.setImageResource(R.drawable.shuffling);
    }

    @Override
    public void timeLine(String elapsedTime, int current) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.timeStart.setText(elapsedTime);
                binding.process.setProgress(current);
                if(binding.timeStart.getText().equals(binding.timeFinish.getText())){
                    binding.play.setImageResource(R.drawable.play);
                    stopRotate();
                    listenListPresenter.next();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        listenListPresenter.onBackPressed();
        finish();
    }
}