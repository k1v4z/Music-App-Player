package com.example.musicapp.presenter.registerpresenter;

public interface RegisterInterface {
    void registerSuccess(String response);
    void registerFail(String response);
    void defect(String response);
}
