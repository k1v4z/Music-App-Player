package com.example.musicapp.presenter.listenpresenter;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.example.musicapp.model.Song;



public class ListenPresenter {
    MediaPlayer musicPlayer;
    Context context;
    boolean isReplay = false;
    Thread thread;
    ListenInterface listenInterface;

    public ListenPresenter(ListenInterface listenInterface) {
        this.listenInterface = listenInterface;
    }

    public void listenMusicBeLike(Intent intent, Context context) {
        Bundle bundle = intent.getExtras();
        this.context = context;
        if (bundle == null) {
            return;
        }
        Song song = (Song) bundle.get("song");
        if (song != null) {
            listenInterface.listenMusicBeLike(song);
            runMusic(song);
        }
    }
    public void runMusic(Song song) {
        musicPlayer = new MediaPlayer();
        musicPlayer = MediaPlayer.create(context, Uri.parse(song.getLinkSong()));
        musicPlayer.setLooping(false);
        musicPlayer.seekTo(0);
        musicPlayer.setVolume(1.5f, 1.5f);
        musicPlayer.start();
        setUpMusic();
        startRotate();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicPlayer != null) {
                    try{
                        if (musicPlayer.isPlaying()) {
                            try {
                                int current = musicPlayer.getCurrentPosition();
                                String elapsedTime = milliSecondsToString(current);
                                listenInterface.timeLine(elapsedTime,current);
                                Thread.sleep(0);
                            } catch (InterruptedException e) {
                            }
                        }
                    }catch(IllegalStateException e){

                    }
                }
            }
        });
        thread.start();
    }
    public void startRotate() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                start(this);
            }
        };
        start(runnable);
    }
    public void stopRotate() {
        stop();
    }

    public String milliSecondsToString(int time) {
        String elapsedTime = "";
        int minute = time / 1000 / 60;
        int second = time / 1000 % 60;
        elapsedTime = minute + ":";
        if (second < 10) {
            elapsedTime += "0";
        }
        elapsedTime += second;
        return elapsedTime;
    }
    public void onBackPressed(){
        musicPlayer.release();
        thread.interrupt();
    }
    public void checkReplay(){
        if(!isReplay){
            isReplay = true;
            musicPlayer.setLooping(true);
            listenInterface.isReplay();
        }else{
            isReplay = false;
            musicPlayer.setLooping(false);
            listenInterface.notReplay();
        }
    }
    public void seek(int progress,boolean fromUser){
        if (fromUser) {
            musicPlayer.seekTo(progress);
            listenInterface.seek(progress);
        }
    }
    public void setUpMusic(){
        listenInterface.setUpMusic(musicPlayer);
    }
    public void start(Runnable runnable){
        listenInterface.startRotate(runnable);
    }
    public void stop(){
        listenInterface.stopRotate();
    }
    public void controlMusic(){
        if (musicPlayer.isPlaying()) {
            musicPlayer.pause();
            stopRotate();
            listenInterface.pause();
        } else {
            musicPlayer.start();
            startRotate();
            listenInterface.play();
        }
    }
}
