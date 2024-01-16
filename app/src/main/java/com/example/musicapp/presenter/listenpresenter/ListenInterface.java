package com.example.musicapp.presenter.listenpresenter;

import android.media.MediaPlayer;

import com.example.musicapp.model.Song;

public interface ListenInterface {
    void listenMusicBeLike(Song song);
    void setUpMusic(MediaPlayer mediaPlayer);
    void startRotate(Runnable runnable);
    void stopRotate();
    void seek(int progress);
    void play();
    void pause();
    void isReplay();
    void notReplay();
    void timeLine(String elapsedTime,int current);
}
