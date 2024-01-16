package com.example.musicapp.presenter.playlistpresenter;


import android.util.Log;

import com.example.musicapp.home.HomeActivity;

import com.example.musicapp.model.State;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlayListPresenter {
    PlayListInterface playListInterface;

    public PlayListPresenter(PlayListInterface playListInterface) {
        this.playListInterface = playListInterface;
    }

    public void updateDataInPlaylist(){
        Gson gson = new Gson();
        ApiService apiService = DataService.getApi();
        Call<State> callback = apiService.upPlayListToServer(gson.toJson(HomeActivity.playList),HomeActivity.userName);
        callback.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        Log.d("TAG",response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });
    }
}
