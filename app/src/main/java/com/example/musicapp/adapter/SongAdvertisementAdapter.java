package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.databinding.ItemSongAdvertiseBinding;
import com.example.musicapp.model.Song;
import com.example.musicapp.my_interface.IClickItemListener;

import java.util.List;

public class SongAdvertisementAdapter extends RecyclerView.Adapter<SongAdvertisementAdapter.SongAdvertisementViewHolder> {
    List<Song> list;
    IClickItemListener listener;

    public SongAdvertisementAdapter(List<Song> list, IClickItemListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SongAdvertisementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongAdvertiseBinding binding = ItemSongAdvertiseBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SongAdvertisementViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdvertisementViewHolder holder, int position) {
        Song song = list.get(position);
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
        return list.size();
    }

    public class SongAdvertisementViewHolder extends RecyclerView.ViewHolder {
        ItemSongAdvertiseBinding binding;
        public SongAdvertisementViewHolder(@NonNull ItemSongAdvertiseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
