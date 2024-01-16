package com.example.musicapp.presenter.listenlistsongpresenter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;


import com.example.musicapp.model.Song;

import java.util.List;
import java.util.Random;

public class ListenListPresenter {
    ListenListInterface listenListInterface;
    Context context;
    List<Song> list;
    MediaPlayer musicPlayer;
    int position; //vị trí bài hát hiện tại
    boolean isReplay = false;
    boolean isShuffling = false;
    Thread thread;
    Random random = new Random();
    public ListenListPresenter(ListenListInterface listenListInterface) {
        this.listenListInterface = listenListInterface;
    }
    public void listenListMusic(Intent intent,Context context){
        Bundle bundle = intent.getExtras();
        this.context = context;
        if (bundle == null) {
            return;
        }
        list = (List<Song>) bundle.get("song");
        position = bundle.getInt("firstPosition"); //vi tri dau tien khi user click vao
        if (list != null) {
            runMusic(list.get(position));
        }
    }
    public void runMusic(Song song){
        musicPlayer = new MediaPlayer();
        musicPlayer = MediaPlayer.create(context, Uri.parse(song.getLinkSong()));
        musicPlayer.setLooping(false);
        musicPlayer.seekTo(0);
        musicPlayer.setVolume(1.5f, 1.5f);
        setUpMusic();
        listenListInterface.setUpSong(list.get(position));
        musicPlayer.start();
        startRotate();
        if(thread != null){
            thread.interrupt();
        }
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicPlayer != null) {
                    try{
                        if (musicPlayer.isPlaying()) {
                            try {
                                int current = musicPlayer.getCurrentPosition();
                                String elapsedTime = milliSecondsToString(current);
                                listenListInterface.timeLine(elapsedTime,current);
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
    public void setUpMusic(){
        listenListInterface.setUpMusic(musicPlayer);
    }
    public void start(Runnable runnable){
        listenListInterface.startRotate(runnable);
    }
    public void stop(){
        listenListInterface.stopRotate();
    }
    public void controlMusic(){
        if (musicPlayer.isPlaying()) {
            musicPlayer.pause();
            stopRotate();
            listenListInterface.pause();
        } else {
            musicPlayer.start();
            startRotate();
            listenListInterface.play();
        }
    }
    public void nextSong(){
        musicPlayer.release();
        if(isReplay){
            Toast.makeText(context,"Vui lòng tắt replay trước khi chuyển bài",Toast.LENGTH_SHORT).show();
            return;
        }
        if(isShuffling){
            int tempPosition = random.nextInt(list.size());
            if(tempPosition == position){
                tempPosition++;
            }
            position = tempPosition;
        }else{
            position++;
        }
        if(position > list.size()-1){
            position =  0;
            //Nếu đã nghe tới cuối bài nhấn next quay về bài đầu tiên
            runMusic(list.get(position));
        }else{
            runMusic(list.get(position));
        }
    }
    public void previousSong(){
        musicPlayer.release();
        position--;
        if(position < 0){
            //Nếu nghe bài đầu tiên nhấn previous button sẽ quay về bài cuối cùng
            position = list.size()-1;
            runMusic(list.get(position));
        }else{
            runMusic(list.get(position));
        }
    }
    //sau khi nghe xong tự động next sang bài khác
    public void next(){
        if(!isReplay){
            nextSong();
        }else{
            listenListInterface.play();
            startRotate();
        }
    }
    public void startRotate(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                start(this);
            }
        };
        start(runnable);
    }
    public void stopRotate(){
        stop();
    }
    public void seek(int progress,boolean fromUser){
        if (fromUser) {
            musicPlayer.seekTo(progress);
            listenListInterface.seek(progress);
        }
    }
    public void checkReplay(){
        if(!isReplay){
            isReplay = true;
            isShuffling = false;
            listenListInterface.notShuffling();
            musicPlayer.setLooping(true);
            listenListInterface.isReplay();
        }else{
            isReplay = false;
            musicPlayer.setLooping(false);
            listenListInterface.notReplay();
        }
    }
    public void checkShuffling(){
        if(!isShuffling){
            isShuffling = true;
            isReplay = false;  // trộn bài thì k thể replay cùng lúc
            musicPlayer.setLooping(false);
            listenListInterface.notReplay();
            listenListInterface.isShuffling();
        }else{
            isShuffling = false;
            listenListInterface.notShuffling();
        }
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
}
