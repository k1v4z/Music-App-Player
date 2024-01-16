package com.example.musicapp.presenter.editpresenter;

import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.model.State;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPresenter {
    EditInterface editInterface;

    public EditPresenter(EditInterface editInterface) {
        this.editInterface = editInterface;
    }
    public void update(String name,String date){
        ApiService apiService = DataService.getApi();
        Call<State> callBack = apiService.updateInformation(name,date, HomeActivity.userName);
        callBack.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                State state = null;
                if (response.isSuccessful()) {
                    state = response.body();
                    editInterface.updateSuccess(state);
                } else {
                    state = response.body();
                    editInterface.updateFail(state);
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });
    }
}
