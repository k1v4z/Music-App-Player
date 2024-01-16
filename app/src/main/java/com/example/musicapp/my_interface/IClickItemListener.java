package com.example.musicapp.my_interface;

import com.example.musicapp.model.Song;

public interface IClickItemListener {
    void onClickSong(Song song, int vt);
    void onClickAddSong(Song song);
}
