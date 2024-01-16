package com.example.musicapp.presenter.settingpresenter;

import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.model.Users;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPresenter {
    SettingInterface settingInterface;

    public SettingPresenter(SettingInterface settingInterface) {
        this.settingInterface = settingInterface;
    }

    public void getData(){
        ApiService apiService = DataService.getApi();
        Call<List<Users>> callBack = apiService.getInformation(HomeActivity.userName);
        callBack.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                ArrayList<Users> list = (ArrayList<Users>) response.body();
                if (list != null) {
                    settingInterface.setDataIntoView(list.get(0).getFullName(),
                                                        list.get(0).getDateOfBirth());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }
}
