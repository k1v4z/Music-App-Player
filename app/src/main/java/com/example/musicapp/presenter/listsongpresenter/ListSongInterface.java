package com.example.musicapp.presenter.listsongpresenter;

import android.content.Intent;
import android.graphics.Bitmap;

import com.example.musicapp.model.Song;

import java.util.List;

public interface ListSongInterface {
    void listen(Intent intent, List<Song> songList);
    void setBitMapDrawable(Bitmap bitmap);
    void setDataOnListSong(List<Song> list);
}
