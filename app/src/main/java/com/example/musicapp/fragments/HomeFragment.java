package com.example.musicapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.musicapp.adapter.AlbumAdapter;
import com.example.musicapp.adapter.AdvertisementAdapter;
import com.example.musicapp.adapter.SongAdapter;
import com.example.musicapp.databinding.FragmentHomeBinding;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.presenter.homepresenter.HomeInterface;
import com.example.musicapp.presenter.homepresenter.HomePresenter;
import com.example.musicapp.presenter.listsongpresenter.ListSong;
import com.example.musicapp.margin.AlbumMargin;
import com.example.musicapp.model.Album;
import com.example.musicapp.model.ArtistOfYear;
import com.example.musicapp.model.Song;
import com.example.musicapp.my_interface.IClickAlbumListener;
import com.example.musicapp.my_interface.IClickItemListener;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;


public class HomeFragment extends Fragment implements HomeInterface{
    FragmentHomeBinding binding;
    HomeActivity homeActivity;
    HomePresenter homePresenter;
    SongAdapter songLikeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeActivity = (HomeActivity) getActivity();
        binding  = FragmentHomeBinding.inflate(inflater,container,false);
        homePresenter = new HomePresenter(this);
        homePresenter.innate(homeActivity,binding.viewPagerHeader);
        homePresenter.getArtist();
        homePresenter.getListSongMayBeLike(homeActivity);
        homePresenter.getListAlbum(homeActivity);
        homePresenter.getPlayList();
        seeMoreType();
        seeMoreAlbum();
        viewAcoustic();
        viewHiphop();
        viewRap();
        viewRock();
        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(homePresenter.timer != null){
            homePresenter.timer.cancel();
            homePresenter.timer = null;
        }
    }
    @Override
    public void listen(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void innate(AdvertisementAdapter adapter) {
        binding.viewPagerHeader.setAdapter(adapter);
        binding.circleIndicator.setViewPager(binding.viewPagerHeader);
        adapter.registerDataSetObserver(binding.circleIndicator.getDataSetObserver());
    }

    @Override
    public void setItem(int position) {
        binding.viewPagerHeader.setCurrentItem(position,true);
    }

    @Override
    public void setFirstItem() {
        binding.viewPagerHeader.setCurrentItem(0,true);
    }

    @Override
    public void innateSongMayBeLike(List<Song> list) {
        songLikeAdapter = new SongAdapter(list, homeActivity, new IClickItemListener() {
            @Override
            public void onClickSong(Song song, int vt) {
                homePresenter.listen(song,homeActivity);
            }

            @Override
            public void onClickAddSong(Song song) {
                if(HomeActivity.playList.containsKey(song.getIdSong())){
                    Toast.makeText(homeActivity,SearchFragment.MESSAGE_ADD_FAIL,Toast.LENGTH_SHORT).show();
                }else{
                    HomeActivity.playList.put(song.getIdSong(),song);
                    Toast.makeText(homeActivity,SearchFragment.MESSAGE_ADD_SUCCESS,Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.rcListSongMaybeLike.setLayoutManager(new LinearLayoutManager(homeActivity, RecyclerView.VERTICAL,false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(homeActivity,DividerItemDecoration.VERTICAL);
        binding.rcListSongMaybeLike.addItemDecoration(dividerItemDecoration);
        binding.rcListSongMaybeLike.setAdapter(songLikeAdapter);
    }

    @Override
    public void innateAlbum(List<Album> list) {
        AlbumAdapter albumAdapter = new AlbumAdapter(list, homeActivity, new IClickAlbumListener() {
            @Override
            public void onCLickAlbum(String idAlbum,String image) {
                Intent intent = new Intent(homeActivity,ListSong.class);
                intent.putExtra("idAlbum",idAlbum);
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });
        binding.rcAlbum.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), RecyclerView.HORIZONTAL,false));
        AlbumMargin albumMargin = new AlbumMargin();
        binding.rcAlbum.addItemDecoration(albumMargin);
        binding.rcAlbum.setAdapter(albumAdapter);
    }

    @Override
    public void seeMoreType(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void seeMoreAlbum(Intent intent) {
        startActivity(intent);
    }

    public void seeMoreType(){
        binding.seemoreType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.seeMoreType(homeActivity);
            }
        });
    }
    public void seeMoreAlbum(){
        binding.seeMoreAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.seeMoreAlbum(homeActivity);
            }
        });
    }
    @Override
    public void setImage(List<ArtistOfYear> list) {
        Glide.with(homeActivity).load(list.get(0).getImage()).into(binding.artist1);
        Glide.with(homeActivity).load(list.get(1).getImage()).into(binding.artist2);
    }

    @Override
    public void viewSongAdvertisement(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void getPlaylist(String json) {
        if(!json.equals("{}")){
            Gson gson = new Gson();
            Type type = com.google.gson.reflect.TypeToken.getParameterized(ArrayMap.class, String.class, Song.class).getType();
            ArrayMap<String,Song>  arrMap = gson.fromJson(json,type);
            HomeActivity.playList = arrMap;
        }
    }

    @Override
    public void networkErrorMessage(String message) {
        Toast.makeText(homeActivity,message,Toast.LENGTH_SHORT).show();
    }

    public void viewAcoustic(){
        binding.type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, ListSong.class);
                intent.putExtra("idCategory","1"); // Id Acoustic in database is 1
                startActivity(intent);
            }
        });
    }
    public void viewHiphop(){
        binding.type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, ListSong.class);
                intent.putExtra("idCategory","2"); // Id Acoustic in database is 2
                startActivity(intent);
            }
        });
    }
    public void viewRock(){
        binding.type3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, ListSong.class);
                intent.putExtra("idCategory","5"); // Id Acoustic in database is 5
                startActivity(intent);
            }
        });
    }
    public void viewRap(){
        binding.type4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, ListSong.class);
                intent.putExtra("idCategory","4"); // Id Acoustic in database is 4
                startActivity(intent);
            }
        });
    }

}