package com.example.musicapp.presenter.homepresenter;

import android.content.Intent;

import com.example.musicapp.adapter.AdvertisementAdapter;
import com.example.musicapp.model.Album;
import com.example.musicapp.model.ArtistOfYear;
import com.example.musicapp.model.Song;

import java.util.List;

public interface HomeInterface {
    void listen(Intent intent);
    void innate(AdvertisementAdapter adapter);
    void setItem(int position);
    void setFirstItem();
    void innateSongMayBeLike(List<Song> list);
    void innateAlbum(List<Album> list);
    void seeMoreType(Intent intent);
    void seeMoreAlbum(Intent intent);
    void setImage(List<ArtistOfYear> list);
    void viewSongAdvertisement(Intent intent);
    void getPlaylist(String json);
    void networkErrorMessage(String message);
}
