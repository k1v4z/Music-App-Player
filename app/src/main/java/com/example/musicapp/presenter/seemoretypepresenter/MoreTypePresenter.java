package com.example.musicapp.presenter.seemoretypepresenter;

import android.content.Context;
import android.content.Intent;

import com.example.musicapp.adapter.AllTypeAdapter;
import com.example.musicapp.presenter.listsongpresenter.ListSong;
import com.example.musicapp.model.Category;
import com.example.musicapp.my_interface.IClickType;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreTypePresenter {
    List<Category> listCategory;
    MoreTypeInterface moreTypeInterface;
    Context context;
    public MoreTypePresenter(MoreTypeInterface moreTypeInterface,Context context) {
        this.moreTypeInterface = moreTypeInterface;
        this.context = context;
    }
    public void innateType(){
        ApiService apiService = DataService.getApi();
        Call<List<Category>> call = apiService.getAllListCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                ArrayList<Category> list = (ArrayList<Category>) response.body();
                listCategory = list;
                AllTypeAdapter seeMoreTypeAdapter = new AllTypeAdapter(listCategory,context,new IClickType() {
                    @Override
                    public void iClickType(String name) {
                        Intent intent = new Intent(context, ListSong.class);
                        intent.putExtra("idCategory",name);
                        moreTypeInterface.seeListSong(intent);
                    }
                });
                moreTypeInterface.innateType(seeMoreTypeAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

    }
}
