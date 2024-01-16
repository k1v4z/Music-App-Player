package com.example.musicapp.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.MenuItem;

import com.example.musicapp.R;
import com.example.musicapp.adapter.HomeViewPagerAdapter;
import com.example.musicapp.databinding.ActivityHomeBinding;
import com.example.musicapp.model.Song;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    public static String userName;
    public static ArrayMap<String,Song> playList = new ArrayMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViewPager();
        userName = getIntent().getStringExtra("userName");
        binding.bottomNV.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    binding.viewPager2.setCurrentItem(0,true);
                }else if(item.getItemId() == R.id.playlist){
                    binding.viewPager2.setCurrentItem(1,true);
                }else if(item.getItemId() == R.id.personal){
                    binding.viewPager2.setCurrentItem(2,true);
                }else{
                    binding.viewPager2.setCurrentItem(3,true);
                }
                return true;
            }
        });
    }
    public void setUpViewPager(){
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(this);
        binding.viewPager2.setAdapter(adapter);
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        binding.bottomNV.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNV.getMenu().findItem(R.id.playlist).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNV.getMenu().findItem(R.id.personal).setChecked(true);
                        break;
                    case 3:
                        binding.bottomNV.getMenu().findItem(R.id.setting).setChecked(true);
                        break;
                }
            }
        });
    }
}