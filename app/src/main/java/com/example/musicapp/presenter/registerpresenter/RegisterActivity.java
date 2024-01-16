package com.example.musicapp.presenter.registerpresenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicapp.MainActivity;
import com.example.musicapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements RegisterInterface {
    RegisterPresenter registerPresenter;
    ActivityRegisterBinding binding;
    public final String MESSAGE_LENGTH_ACCOUNT = "Tài khoản phải có ít nhất 6 kí tự";
    public final String MESSAGE_LENGTH_PASSWORD = "Mật khẩu phải có ít nhất 6 kí tự";
    public final String MESSAGE_REENTER_PASSWORD = "Mật khẩu chưa khớp";
    public final String REGISTER_SUCCESS = "Đăng ký thành công";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registerPresenter = new RegisterPresenter(this,this);
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    boolean checkRegister(String userName,String password,String retryPassword ){
        if(userName.length() < 6){
            Toast.makeText(RegisterActivity.this,MESSAGE_LENGTH_ACCOUNT,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length() < 6){
            Toast.makeText(RegisterActivity.this,MESSAGE_LENGTH_PASSWORD, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.equals(retryPassword)){
            Toast.makeText(RegisterActivity.this,MESSAGE_REENTER_PASSWORD,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void register(){
        String userName = binding.edtUserNameRe.getText().toString().trim();
        String password = binding.edtPasswordRe.getText().toString().trim();
        String retryPassword = binding.edtRetry.getText().toString().trim();
        if(checkRegister(userName,password,retryPassword)){
            registerPresenter.register(userName,password);
        }
    }

    @Override
    public void registerSuccess(String response) {
        Toast.makeText(RegisterActivity.this,response,
                Toast.LENGTH_SHORT).show();
        if(response.equals(REGISTER_SUCCESS)){
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void registerFail(String response) {
        Toast.makeText(RegisterActivity.this,response,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void defect(String response) {
        Toast.makeText(RegisterActivity.this,response,
                Toast.LENGTH_SHORT).show();
    }
}