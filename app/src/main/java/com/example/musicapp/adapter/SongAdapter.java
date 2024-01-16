package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.model.Song;
import com.example.musicapp.databinding.ItemSongBinding;
import com.example.musicapp.my_interface.IClickItemListener;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongMayBeLikeViewHolder> {
    List<Song> list;
    IClickItemListener listener;
    HomeActivity homeActivity;
    public SongAdapter(List<Song> list, HomeActivity homeActivity, IClickItemListener listener) {
        this.list = list;
        this.listener = listener;
        this.homeActivity = homeActivity;
    }

    @NonNull
    @Override
    public SongMayBeLikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongBinding binding = ItemSongBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SongMayBeLikeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongMayBeLikeViewHolder holder, int position) {
        Song song = list.get(position);
        Glide.with(homeActivity).load(song.getThumb())
                .override(140,140).into(holder.binding.thumb);
        holder.binding.name.setText(song.getNameSong());
        holder.binding.authorr.setText(song.getAuthor());
        holder.binding.layoutSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vt;
                vt = holder.getLayoutPosition();
                listener.onClickSong(song,vt);
            }
        });
        holder.binding.addSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickAddSong(song);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class SongMayBeLikeViewHolder extends RecyclerView.ViewHolder {
        ItemSongBinding binding;
        public SongMayBeLikeViewHolder(@NonNull ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
