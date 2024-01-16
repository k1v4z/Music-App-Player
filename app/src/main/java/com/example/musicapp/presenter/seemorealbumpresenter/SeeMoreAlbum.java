package com.example.musicapp.presenter.seemorealbumpresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.musicapp.adapter.AllAlbumAdapter;
import com.example.musicapp.databinding.ActivitySeeMoreAlbumBinding;
import com.example.musicapp.margin.AllAlbumMargin;

public class SeeMoreAlbum extends AppCompatActivity implements SeeMoreAlbumInterface {
    ActivitySeeMoreAlbumBinding binding;
    SeeMoreAlbum seeMoreAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeeMoreAlbumBinding.inflate(getLayoutInflater());
        SeeMoreAlbumPresenter presenter = new SeeMoreAlbumPresenter(this,SeeMoreAlbum.this);
        presenter.innateAlbum(SeeMoreAlbum.this);
        onBack();
        setContentView(binding.getRoot());
    }

    @Override
    public void innateAlbum(AllAlbumAdapter albumAdapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        AllAlbumMargin margin = new AllAlbumMargin();
        binding.rcAlbum.addItemDecoration(margin);
        binding.rcAlbum.setLayoutManager(gridLayoutManager);
        binding.rcAlbum.setAdapter(albumAdapter);
    }

    @Override
    public void seeListSong(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void onBack(){
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}