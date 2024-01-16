package com.example.musicapp.model;

public class PlayList {
    private String key;
    private Song song;

    public PlayList(String key, Song song) {
        this.key = key;
        this.song = song;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
