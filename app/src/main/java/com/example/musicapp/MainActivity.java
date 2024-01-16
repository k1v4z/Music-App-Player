package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.presenter.loginpresenter.LogInterface;
import com.example.musicapp.presenter.loginpresenter.LogPresenter;
import com.example.musicapp.databinding.ActivityMainBinding;
import com.example.musicapp.presenter.registerpresenter.RegisterActivity;

public class MainActivity extends AppCompatActivity implements LogInterface {
    ActivityMainBinding binding;
    LogPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginPresenter = new LogPresenter(this,this);
        binding.tvNotice.setVisibility(View.GONE);
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        String userName = binding.edtUserName.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        loginPresenter.login(userName,password);
    }
    public void register(){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    @Override
    public void loginSuccess(String state) {
        binding.tvNotice.setVisibility(View.VISIBLE);
        binding.tvNotice.setTextColor(getResources().getColor(R.color.green,null));
        binding.tvNotice.setText(state);
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("userName",binding.edtUserName.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(String state) {
        binding.tvNotice.setVisibility(View.VISIBLE);
        binding.tvNotice.setTextColor(getResources().getColor(R.color.red,null));
        binding.tvNotice.setText(state);
    }
    @Override
    public void defect(String state) {
        Toast.makeText(MainActivity.this,state, Toast.LENGTH_SHORT).show();
    }
}