package com.example.musicapp.presenter.seemoretypepresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.musicapp.adapter.AllTypeAdapter;
import com.example.musicapp.databinding.ActivitySeeMoreTypeBinding;
import com.example.musicapp.margin.TypeMargin;


public class SeeMoreType extends AppCompatActivity implements MoreTypeInterface {
    ActivitySeeMoreTypeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeeMoreTypeBinding.inflate(getLayoutInflater());
        MoreTypePresenter moreTypePresenter = new MoreTypePresenter(this,SeeMoreType.this);
        moreTypePresenter.innateType();
        onBack();
        setContentView(binding.getRoot());
    }

    @Override
    public void innateType(AllTypeAdapter seeMoreTypeAdapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        TypeMargin categoryMargin = new TypeMargin();
        binding.rcType.addItemDecoration(categoryMargin);
        binding.rcType.setLayoutManager(gridLayoutManager);
        binding.rcType.setAdapter(seeMoreTypeAdapter);
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