package com.example.musicapp.presenter.seemorealbumpresenter;

import android.content.Context;
import android.content.Intent;

import com.example.musicapp.adapter.AllAlbumAdapter;
import com.example.musicapp.presenter.listsongpresenter.ListSong;
import com.example.musicapp.model.Album;
import com.example.musicapp.my_interface.IClickAlbum;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeMoreAlbumPresenter {
    SeeMoreAlbumInterface seeMoreAlbumInterface;
    List<Album> allAlbum;
    Context context;
    public SeeMoreAlbumPresenter(SeeMoreAlbumInterface seeMoreAlbumInterface,Context context) {
        this.seeMoreAlbumInterface = seeMoreAlbumInterface;
        this.context = context;
    }
    public void innateAlbum(SeeMoreAlbum seeMoreAlbum){
        ApiService apiService = DataService.getApi();
        Call<List<Album>> call = apiService.getAllListAlbum();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> list = (ArrayList<Album>) response.body();
                allAlbum = list;

                AllAlbumAdapter albumAdapter = new AllAlbumAdapter(allAlbum, seeMoreAlbum, new IClickAlbum() {
                    @Override
                    public void OnClickAlbum(String name, String image) {
                        Intent intent = new Intent(context, ListSong.class);
                        intent.putExtra("idAlbum",name);
                        intent.putExtra("image",image);
                        seeMoreAlbumInterface.seeListSong(intent);
                    }
                });
                seeMoreAlbumInterface.innateAlbum(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
