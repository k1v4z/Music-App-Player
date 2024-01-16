package com.example.musicapp.presenter.loginpresenter;

import android.content.Context;

import com.example.musicapp.model.State;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogPresenter {
    LogInterface loginInterface;
    public Context context;
    public final String ERROR_CODE_ERROR_1 = "001";
    public final String ERROR_CODE_ERROR_2 = "003";
    public final String ERROR_CODE_SUCCESS = "002";
    public final String NETWORK_ERROR_MESSAGE = "Vui lòng kiểm tra lại kết nối mạng";
    public LogPresenter(LogInterface loginInterface, Context context){
        this.loginInterface = loginInterface;
        this.context = context;
    }
    public void login(String userName,String password){
        ApiService apiService = DataService.getApi();
        Call<State> callApiLogin = apiService.login(userName,password);
        callApiLogin.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                State state = response.body();
                if (state != null && state.getError().equals(ERROR_CODE_ERROR_1)) {
                    loginInterface.loginFail(state.getMessage());
                }
                if(state != null && state.getError().equals(ERROR_CODE_SUCCESS)){
                    loginInterface.loginSuccess(state.getMessage());
                }
                if(state != null && state.getError().equals(ERROR_CODE_ERROR_2)){
                    loginInterface.loginFail(state.getMessage());
                }
            }
            @Override
            public void onFailure(Call<State> call, Throwable t) {
                loginInterface.defect(NETWORK_ERROR_MESSAGE);
            }
        });
    }
}
