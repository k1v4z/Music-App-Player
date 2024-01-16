package com.example.musicapp.presenter.editpresenter;

import com.example.musicapp.model.State;

public interface EditInterface {
    void updateSuccess(State state);
    void updateFail(State state);
}
