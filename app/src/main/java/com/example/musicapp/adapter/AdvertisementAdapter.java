package com.example.musicapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.musicapp.R;
import com.example.musicapp.presenter.homepresenter.HomeInterface;
import com.example.musicapp.presenter.listsongpresenter.ListSong;
import com.example.musicapp.model.Advertisement;

import java.util.List;

public class AdvertisementAdapter extends PagerAdapter {
    private Context context;
    private List<Advertisement> list;
    HomeInterface homeInterface;
    ImageView img;
    public AdvertisementAdapter(Context context, List<Advertisement> list,HomeInterface homeInterface) {
        this.context = context;
        this.list = list;
        this.homeInterface =  homeInterface;
    }

    @Override
    public int getCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        img = view.findViewById(R.id.slideAdvertise);
        Advertisement advertisement = list.get(position);
        Glide.with(context).load(advertisement.getImage()).into(img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListSong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("advertisement",advertisement);
                intent.putExtras(bundle);
                homeInterface.viewSongAdvertisement(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
