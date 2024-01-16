package com.example.musicapp.presenter.editpresenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.musicapp.databinding.ActivityEditBinding;
import com.example.musicapp.model.State;

public class EditActivity extends AppCompatActivity implements EditInterface {
    ActivityEditBinding binding;
    EditPresenter editPresenter;
    String userName,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        editPresenter = new EditPresenter(this);
        getData();
        binding.edtName.setText(userName);
        binding.edtDate.setText(date);
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG",binding.edtName.getText().toString());
                editPresenter.update(binding.edtName.getText().toString(),
                                     binding.edtDate.getText().toString());
            }
        });
        setContentView(binding.getRoot());
    }
    public void getData(){
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        date = intent.getStringExtra("date");
    }

    @Override
    public void updateSuccess(State state) {
        Toast.makeText(this,state.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateFail(State state) {
        Toast.makeText(this,state.getMessage(),Toast.LENGTH_SHORT).show();
    }
}