package com.example.musicapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.musicapp.adapter.PlayListAdapter;
import com.example.musicapp.databinding.FragmentPlaylistBinding;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.presenter.listenlistsongpresenter.ListenListSong;
import com.example.musicapp.model.Song;
import com.example.musicapp.my_interface.IClickDelete;
import com.example.musicapp.my_interface.IClickItemListener;
import com.example.musicapp.presenter.playlistpresenter.PlayListInterface;
import com.example.musicapp.presenter.playlistpresenter.PlayListPresenter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlaylistFragment extends Fragment implements PlayListInterface, SwipeRefreshLayout.OnRefreshListener {
    FragmentPlaylistBinding binding;
    PlayListAdapter adapter;
    HomeActivity homeActivity;
    PlayListPresenter playListPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding  = FragmentPlaylistBinding.inflate(inflater,container,false);
        playListPresenter = new PlayListPresenter(this);
        homeActivity = (HomeActivity) getActivity();
        setDataIntoView();
        binding.swipeRefresh.setOnRefreshListener(this);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    public void setDataIntoView(){
        adapter = new PlayListAdapter(homeActivity, new IClickDelete() {
            @Override
            public void onClickDelete(Song song) {
                HomeActivity.playList.remove(song.getIdSong());
                adapter.notifyDataSetChanged();
            }
        }, new IClickItemListener() {
            @Override
            public void onClickSong(Song song, int vt) {
                List<Song> list = new ArrayList<Song>(HomeActivity.playList.values());
                Intent intent = new Intent(homeActivity, ListenListSong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song", (Serializable) list);
                bundle.putInt("firstPosition",vt);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onClickAddSong(Song song) {

            }
        });
        binding.rcSong.setLayoutManager(new LinearLayoutManager(homeActivity,LinearLayoutManager.VERTICAL,false));
        binding.rcSong.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onRefresh() {
        //load lại fragment playlist để update dữ liệu lên server
        playListPresenter.updateDataInPlaylist();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.swipeRefresh.setRefreshing(false);
                Toast.makeText(homeActivity,"Cập nhật bài hát thành công",Toast.LENGTH_SHORT).show();
            }
        },3000);
    }
}