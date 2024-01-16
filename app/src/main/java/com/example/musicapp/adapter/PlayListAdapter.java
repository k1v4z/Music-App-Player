package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.databinding.ItemPlaylistBinding;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.model.Song;
import com.example.musicapp.my_interface.IClickDelete;
import com.example.musicapp.my_interface.IClickItemListener;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder> {
    IClickDelete listener;
    IClickItemListener iClickItemListener;
    HomeActivity homeActivity;

    public PlayListAdapter(HomeActivity homeActivity,IClickDelete listener,IClickItemListener iClickItemListener) {
        this.listener = listener;
        this.homeActivity = homeActivity;
        this.iClickItemListener = iClickItemListener;
    }

    @NonNull
    @Override
    public PlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlaylistBinding binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PlayListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListViewHolder holder, int position) {
        Song song = HomeActivity.playList.valueAt(position);
        Glide.with(homeActivity).load(song.getThumb())
                .override(140,140).into(holder.binding.thumb);
        holder.binding.name.setText(song.getNameSong());
        holder.binding.authorr.setText(song.getAuthor());
        holder.binding.deleteSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDelete(song);
            }
        });
        holder.binding.layoutSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickSong(song,holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(HomeActivity.playList != null){
            return HomeActivity.playList.size();
        }
        return 0;
    }

    public class PlayListViewHolder extends RecyclerView.ViewHolder {
        ItemPlaylistBinding binding;
        public PlayListViewHolder(@NonNull ItemPlaylistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
