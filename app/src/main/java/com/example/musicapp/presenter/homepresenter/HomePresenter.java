package com.example.musicapp.presenter.homepresenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.presenter.listenpresenter.ListenActivity;
import com.example.musicapp.model.Advertisement;
import com.example.musicapp.model.ArtistOfYear;
import com.example.musicapp.model.Users;
import com.example.musicapp.presenter.seemorealbumpresenter.SeeMoreAlbum;
import com.example.musicapp.presenter.seemoretypepresenter.SeeMoreType;
import com.example.musicapp.model.Song;
import com.example.musicapp.adapter.AdvertisementAdapter;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.model.Album;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private HomeInterface homeInterface;
    List<Advertisement> advertisementList;
    List<ArtistOfYear> artistList;
    AdvertisementAdapter advertisementAdapter;
    public Timer timer;
    List<Song> listSongLike;
    List<Album> listAlbum;
    public final String NETWORK_ERROR_MESSAGE = "Vui lòng kiểm tra lại kết nối mạng";
    public HomePresenter(HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
    }
    public void listen(Song song,HomeActivity homeActivity){
        Intent intent = new Intent(homeActivity, ListenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("song",song);
        intent.putExtras(bundle);
        homeInterface.listen(intent);
    }
    public void innate(HomeActivity homeActivity,ViewPager viewPager){
        ApiService apiService = DataService.getApi();
        Call<List<Advertisement>> callBack = apiService.getListAdvertisement();
        callBack.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(Call<List<Advertisement>> call, Response<List<Advertisement>> response) {
                ArrayList<Advertisement> ads = (ArrayList<Advertisement>) response.body();
                advertisementList = ads;
                advertisementAdapter = new AdvertisementAdapter(homeActivity, advertisementList,homeInterface);
                homeInterface.innate(advertisementAdapter);
                slideImage(viewPager);
            }

            @Override
            public void onFailure(Call<List<Advertisement>> call, Throwable t) {
                homeInterface.networkErrorMessage(NETWORK_ERROR_MESSAGE);
            }
        });
    }
    public void slideImage(ViewPager viewPager){
        if(timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int current = viewPager.getCurrentItem();
                        int size = advertisementList.size() - 1;
                        if(current < size){
                            current++;
                            homeInterface.setItem(current);
                        }else{
                            homeInterface.setFirstItem();
                        }
                    }
                });
            }
        },1000,3000);
    }
    public void getArtist(){
        ApiService apiService = DataService.getApi();
        Call<List<ArtistOfYear>> callback = apiService.getListArtist();
        callback.enqueue(new Callback<List<ArtistOfYear>>() {
            @Override
            public void onResponse(Call<List<ArtistOfYear>> call, Response<List<ArtistOfYear>> response) {
                ArrayList<ArtistOfYear> artistOfYears = (ArrayList<ArtistOfYear>) response.body();
                artistList = artistOfYears;
                homeInterface.setImage(artistList);
            }

            @Override
            public void onFailure(Call<List<ArtistOfYear>> call, Throwable t) {
                homeInterface.networkErrorMessage(NETWORK_ERROR_MESSAGE);
            }
        });
    }
    public void getListSongMayBeLike(HomeActivity homeActivity){
        ApiService apiService = DataService.getApi();
        Call<List<Song>> callback = apiService.getListSongMayBeLike();
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songList = (ArrayList<Song>) response.body();
                listSongLike = songList;
                homeInterface.innateSongMayBeLike(listSongLike);
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                homeInterface.networkErrorMessage(NETWORK_ERROR_MESSAGE);
            }
        });
    }
    public void getListAlbum(HomeActivity homeActivity) {
        ApiService apiService = DataService.getApi();
        Call<List<Album>> callback = apiService.getListAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumList = (ArrayList<Album>) response.body();
                listAlbum = albumList;
                homeInterface.innateAlbum(listAlbum);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                homeInterface.networkErrorMessage(NETWORK_ERROR_MESSAGE);
            }
        });
    }
    public void seeMoreType(HomeActivity homeActivity){
        Intent intent = new Intent(homeActivity, SeeMoreType.class);
        homeInterface.seeMoreType(intent);
    }
    public void seeMoreAlbum(HomeActivity homeActivity){
        Intent intent = new Intent(homeActivity, SeeMoreAlbum.class);
        homeInterface.seeMoreAlbum(intent);
    }
    public void getPlayList(){
        ApiService apiService = DataService.getApi();
        Call<List<Users>> callBack = apiService.getPlaylist(HomeActivity.userName);
        callBack.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                ArrayList<Users> user = (ArrayList<Users>) response.body();
                if (user != null) {
                    homeInterface.getPlaylist(user.get(0).getPlayList());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                homeInterface.networkErrorMessage(NETWORK_ERROR_MESSAGE);
            }
        });
    }
}
