package com.example.musicapp.presenter.searchpresenter;

import com.example.musicapp.model.Song;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {
    SearchInterface searchInterface;

    public SearchPresenter(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
    }

    public void getListSong(String key){
        ApiService apiService = DataService.getApi();
        Call<List<Song>> callback = apiService.searchSong(key);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> song = (ArrayList<Song>) response.body();
                if(response.isSuccessful()){
                    searchInterface.success(song);
                }else{
                    searchInterface.fail();
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                searchInterface.fail();
            }
        });
    }
}
