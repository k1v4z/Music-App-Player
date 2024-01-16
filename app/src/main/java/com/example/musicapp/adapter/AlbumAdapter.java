package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.databinding.ItemAlbumBinding;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.model.Album;
import com.example.musicapp.my_interface.IClickAlbumListener;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> listAlbum;
    HomeActivity homeActivity;
    IClickAlbumListener listener;

    public AlbumAdapter(List<Album> listAlbum, HomeActivity homeActivity,IClickAlbumListener listener) {
        this.listAlbum = listAlbum;
        this.homeActivity = homeActivity;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAlbumBinding binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = listAlbum.get(position);
        Glide.with(homeActivity).load(album.getAlbumImage())
                .override(320,320).into(holder.binding.imgAlbum);
        holder.binding.tvName.setText(album.getAlbumName());
        holder.binding.tvSinger.setText(album.getAlbumAuthor());
        holder.binding.layoutSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCLickAlbum(album.getIdAlbum(), album.getAlbumImage());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listAlbum != null){
            return listAlbum.size();
        }
        return 0;
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        private ItemAlbumBinding binding;
        public AlbumViewHolder(@NonNull ItemAlbumBinding itemAlbumBinding) {
            super(itemAlbumBinding.getRoot());
            binding = itemAlbumBinding;
        }
    }
}
