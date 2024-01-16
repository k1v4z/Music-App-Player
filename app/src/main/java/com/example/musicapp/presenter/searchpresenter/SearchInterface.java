package com.example.musicapp.presenter.searchpresenter;

import com.example.musicapp.model.Song;

import java.util.List;

public interface SearchInterface {
    void success(List<Song> list);
    void fail();
}
