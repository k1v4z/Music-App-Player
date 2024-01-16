package com.example.musicapp.presenter.listenlistsongpresenter;

import android.content.Intent;
import android.media.MediaPlayer;

import com.example.musicapp.model.Song;

public interface ListenListInterface {
    void setUpSong(Song song);
    void setUpMusic(MediaPlayer musicPlayer);
    void startRotate(Runnable runnable);
    void stopRotate();
    void seek(int progress);
    void play();
    void pause();
    void isReplay();
    void notReplay();
    void isShuffling();
    void notShuffling();
    void timeLine(String elapsedTime,int current);
}
