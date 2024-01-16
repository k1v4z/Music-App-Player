package com.example.musicapp.presenter.listsongpresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.musicapp.adapter.SongAdvertisementAdapter;
import com.example.musicapp.databinding.ActivityListSongBinding;
import com.example.musicapp.fragments.SearchFragment;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.presenter.listenpresenter.ListenActivity;
import com.example.musicapp.model.Advertisement;
import com.example.musicapp.model.Song;
import com.example.musicapp.my_interface.IClickItemListener;

import java.util.List;

public class ListSong extends AppCompatActivity implements ListSongInterface {
    ActivityListSongBinding binding;
    ListSongPresenter listSongPresenter;
    SongAdvertisementAdapter adapter;
    String background = "https://musicplayerdb.000webhostapp.com/Image/background/background_music.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListSongBinding.inflate(getLayoutInflater());
        listSongPresenter = new ListSongPresenter(this);
        setData();
        setContentView(binding.getRoot());
    }
    public void setData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            //get Song in banner
            Advertisement advertisement = (Advertisement) bundle.get("advertisement");
            if (advertisement != null) {
                listSongPresenter.getSongAdvertisement(advertisement.getIdSong());
                Glide.with(this).load(advertisement.getImage()).into(binding.imgView);
                listSongPresenter.loadBitMap(advertisement.getImage());
            }
            //get Song flowing category
            String idCategory = intent.getStringExtra("idCategory");
            if(idCategory != null){
                listSongPresenter.getSongCategory(idCategory);
                Glide.with(this).load(background).into(binding.imgView);
                listSongPresenter.loadBitMap(background);
            }
            //get Song flowing album
            String idAlbum = intent.getStringExtra("idAlbum");
            String image = intent.getStringExtra("image");
            if(idAlbum != null && image != null){
                listSongPresenter.getSongAlbum(idAlbum);
                Glide.with(this).load(image).into(binding.imgView);
                listSongPresenter.loadBitMap(image);
            }
        }
    }
    public void listen(Song song){
        Intent intent = new Intent(ListSong.this, ListenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("song",song);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void listen(Intent intent, List<Song> songList) {
        startActivity(intent);
    }

    @Override
    public void setBitMapDrawable(Bitmap bitmap) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
        binding.collapsingtoolbar.setBackground(bitmapDrawable);
    }

    @Override
    public void setDataOnListSong(List<Song> list) {
        adapter = new SongAdvertisementAdapter(list, new IClickItemListener() {
            @Override
            public void onClickSong(Song song, int vt) {
                if(list.size() > 1){
                    listSongPresenter.listen(list,ListSong.this,vt);
                }else{
                    listen(song);
                }
            }

            @Override
            public void onClickAddSong(Song song) {
                if(HomeActivity.playList.containsKey(song.getIdSong())){
                    Toast.makeText(ListSong.this, SearchFragment.MESSAGE_ADD_FAIL,Toast.LENGTH_SHORT).show();
                }else{
                    HomeActivity.playList.put(song.getIdSong(),song);
                    Toast.makeText(ListSong.this,SearchFragment.MESSAGE_ADD_SUCCESS,Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.rcListSong.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        binding.rcListSong.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}