package com.example.musicapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.musicapp.R;
import com.example.musicapp.adapter.SongAdapter;
import com.example.musicapp.databinding.FragmentSearchBinding;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.presenter.listenpresenter.ListenActivity;
import com.example.musicapp.model.Song;
import com.example.musicapp.my_interface.IClickItemListener;
import com.example.musicapp.presenter.searchpresenter.SearchInterface;
import com.example.musicapp.presenter.searchpresenter.SearchPresenter;

import java.util.List;

public class SearchFragment extends Fragment implements SearchInterface {
    FragmentSearchBinding binding;
    HomeActivity homeActivity;
    SearchPresenter searchPresenter;
    public static final String MESSAGE_ADD_SUCCESS = "Đã thêm bài hát này vào Playlist!";
    public static final String MESSAGE_ADD_FAIL = "Bài hát này đã tồn tại trong Playlist!";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeActivity = (HomeActivity) getActivity();
        binding = FragmentSearchBinding.inflate(inflater,container,false);
        searchPresenter = new SearchPresenter(this);
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbarSearch);
        binding.toolbarSearch.setTitle("");
        setHasOptionsMenu(true);
        return binding.getRoot();
    }
    //TODO Setting searchView
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                innateListSong(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    //TODO setting recyclerView
    public void innateListSong(String key){
        searchPresenter.getListSong(key);
    }

    @Override
    public void success(List<Song> list) {
        binding.rcSearch.setVisibility(View.VISIBLE);
        binding.notice.setVisibility(View.GONE);
        SongAdapter adapter = new SongAdapter(list, homeActivity, new IClickItemListener() {

            @Override
            public void onClickSong(Song song, int vt) {
                Intent intent = new Intent(homeActivity, ListenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song",song);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onClickAddSong(Song song) {
                if(HomeActivity.playList.containsKey(song.getIdSong())){
                    Toast.makeText(homeActivity,MESSAGE_ADD_FAIL,Toast.LENGTH_SHORT).show();
                }else{
                    HomeActivity.playList.put(song.getIdSong(),song);
                    Toast.makeText(homeActivity,MESSAGE_ADD_SUCCESS,Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.rcSearch.setLayoutManager(new LinearLayoutManager(homeActivity,LinearLayoutManager.VERTICAL,false));
        binding.rcSearch.setAdapter(adapter);
    }

    @Override
    public void fail() {
        binding.rcSearch.setVisibility(View.GONE);
        binding.notice.setVisibility(View.VISIBLE);
    }
}