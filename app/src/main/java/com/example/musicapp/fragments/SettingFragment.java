package com.example.musicapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.MainActivity;
import com.example.musicapp.R;
import com.example.musicapp.presenter.changepasswordpresenter.ChangingActivity;
import com.example.musicapp.databinding.FragmentSettingBinding;
import com.example.musicapp.presenter.editpresenter.EditActivity;
import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.presenter.settingpresenter.SettingInterface;
import com.example.musicapp.presenter.settingpresenter.SettingPresenter;

public class SettingFragment extends Fragment implements SettingInterface {
    FragmentSettingBinding binding;
    SettingPresenter settingPresenter;
    HomeActivity homeActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater,container,false);
        settingPresenter = new SettingPresenter(this);
        setHasOptionsMenu(true);
        binding.toolbar.inflateMenu(R.menu.menu_setting);
        homeActivity = (HomeActivity) getActivity();
        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.edit){
                    Intent intent = new Intent(homeActivity, EditActivity.class);
                    intent.putExtra("userName",binding.edtName.getText().toString());
                    intent.putExtra("date",binding.edtDate.getText().toString());
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(homeActivity, ChangingActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        settingPresenter.getData();
        return binding.getRoot();
    }
    public void logOut(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(homeActivity);
        dialog.setTitle("Thông báo!");
        dialog.setIcon(R.drawable.player);
        dialog.setMessage("Bạn có muốn đăng xuất không ?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(homeActivity, MainActivity.class);
                HomeActivity.userName = null;
                HomeActivity.playList = null;
                startActivity(intent);
                homeActivity.finish();
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_setting,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public void setDataIntoView(String fullName, String date) {
        binding.edtName.setText(fullName);
        binding.edtDate.setText(date);
    }
}