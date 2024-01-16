package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.databinding.AllAlbumBinding;
import com.example.musicapp.model.Album;
import com.example.musicapp.my_interface.IClickAlbum;
import com.example.musicapp.presenter.seemorealbumpresenter.SeeMoreAlbum;


import java.util.List;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.SeeMoreAlbumViewHolder> {
    List<Album> albumList;
    SeeMoreAlbum seeMoreAlbum;
    IClickAlbum listener;
    public AllAlbumAdapter(List<Album> albumList, SeeMoreAlbum seeMoreAlbum, IClickAlbum listener) {
        this.albumList = albumList;
        this.seeMoreAlbum = seeMoreAlbum;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SeeMoreAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AllAlbumBinding binding = AllAlbumBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SeeMoreAlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SeeMoreAlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        Glide.with(seeMoreAlbum).load(album.getAlbumImage())
                                .override(320,320).into(holder.binding.imageAlbum);
        holder.binding.tvNameAlbum.setText(album.getAlbumName());
        holder.binding.tvAuthor.setText(album.getAlbumAuthor());
        holder.binding.layoutSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickAlbum(album.getIdAlbum(), album.getAlbumImage());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(albumList!=null){
            return albumList.size();
        }
        return 0;
    }

    public class SeeMoreAlbumViewHolder extends RecyclerView.ViewHolder {
        AllAlbumBinding binding;
        public SeeMoreAlbumViewHolder(AllAlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
