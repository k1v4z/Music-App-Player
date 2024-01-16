package com.example.musicapp.presenter.registerpresenter;

import android.content.Context;


import com.example.musicapp.model.RegisterResponse;
import com.example.musicapp.service.ApiService;
import com.example.musicapp.service.DataService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    RegisterInterface registerInterface;
    public Context context;
    public final String NETWORK_ERROR_MESSAGE = "Vui lòng kiểm tra lại kết nối mạng";
    public RegisterPresenter(RegisterInterface registerInterface,Context context){
        this.registerInterface = registerInterface;
        this.context = context;
    }
    public void register(String userName,String password){
        ApiService apiService = DataService.getApi();
        Call<RegisterResponse> callApiRegister = apiService.register(userName,password,"{}");
        callApiRegister.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if(response.isSuccessful()){
                    if (registerResponse != null) {
                        registerInterface.registerSuccess(registerResponse.getMessage());
                    }
                }else{
                    if (registerResponse != null) {
                        registerInterface.registerFail(registerResponse.getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerInterface.defect(NETWORK_ERROR_MESSAGE);
            }
        });

    }
}
