package com.example.musicapp.presenter.changepasswordpresenter;

import com.example.musicapp.model.State;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePresenter {
    ChangingInterface changingInterface;

    public ChangePresenter(ChangingInterface changingInterface) {
        this.changingInterface = changingInterface;

    }
    public void changePassword(String userName,String oldPassword,String newPassword){
        ApiService apiService = DataService.getApi();
        Call<State> call = apiService.changePassword(userName, oldPassword, newPassword);
        call.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                State state = null;
                if(response.isSuccessful()){
                    state =  response.body();
                    changingInterface.success(state);
                }else{
                    state = response.body();
                    changingInterface.fail(state);
                }
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });
    }
}
