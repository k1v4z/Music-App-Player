package com.example.musicapp.presenter.changepasswordpresenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicapp.databinding.ActivityChangingBinding;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.model.State;

public class ChangingActivity extends AppCompatActivity implements ChangingInterface {
    ActivityChangingBinding binding;
    ChangePresenter changePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangingBinding.inflate(getLayoutInflater());
        changePresenter = new ChangePresenter(this);
        changePassword();
        setUserName();
        setContentView(binding.getRoot());
    }
    public void changePassword(){
        binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePresenter.changePassword(binding.edtUserName.getText().toString(),
                                               binding.edtOldPassword.getText().toString(),
                                               binding.edtNewPassword.getText().toString());
            }
        });
    }
    public void setUserName(){
        binding.edtUserName.setText(HomeActivity.userName);
    }
    @Override
    public void success(State state) {
        Toast.makeText(this,state.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail(State state) {
        Toast.makeText(this,state.getMessage(),Toast.LENGTH_SHORT).show();
    }
}