package com.example.musicapp.presenter.listsongpresenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;

import com.example.musicapp.presenter.listenlistsongpresenter.ListenListSong;
import com.example.musicapp.model.Song;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongPresenter{
    ListSongInterface listSongInterface;
    int firstPosition;
    public ListSongPresenter(ListSongInterface listSongInterface) {
        this.listSongInterface = listSongInterface;
    }
    public void listen(List<Song> list, ListSong listSong,int vt){
        Intent intent = new Intent(listSong, ListenListSong.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("song", (Serializable) list);
        firstPosition = vt;
        bundle.putInt("firstPosition",firstPosition);
        intent.putExtras(bundle);
        listSongInterface.listen(intent,list);
    }
    public void loadBitMap(String link){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll() // tìm kiếm hết tất cả các lỗi
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(link);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection()
                    .getInputStream());
            listSongInterface.setBitMapDrawable(bitmap);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getSongAdvertisement(String idSong){
        ApiService apiService = DataService.getApi();
        Call<List<Song>> callBack = apiService.getListAdvertisementSong(idSong);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> list = new ArrayList<>();
                list =  response.body();
                listSongInterface.setDataOnListSong(list);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
    public void getSongCategory(String categoryName){
        ApiService apiService = DataService.getApi();
        Call<List<Song>> callBack = apiService.getListCategorySong(categoryName);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> list = new ArrayList<>();
                list = response.body();
                listSongInterface.setDataOnListSong(list);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
    public void getSongAlbum(String idAlbum){
        ApiService apiService = DataService.getApi();
        Call<List<Song>> callBack = apiService.getListAlbumSong(idAlbum);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> list = new ArrayList<>();
                list = response.body();
                listSongInterface.setDataOnListSong(list);
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
