package com.example.musicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.databinding.AllCategoryBinding;
import com.example.musicapp.model.Category;
import com.example.musicapp.my_interface.IClickType;

import java.util.List;

public class AllTypeAdapter extends RecyclerView.Adapter<AllTypeAdapter.seeMoreTypeViewHolder> {
    List<Category> categoryList;
    IClickType iClickType;
    Context context;
    public AllTypeAdapter(List<Category> categoryList, Context context, IClickType iClickType) {
        this.categoryList = categoryList;
        this.iClickType = iClickType;
        this.context = context;
    }

    @NonNull
    @Override
    public seeMoreTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AllCategoryBinding binding = AllCategoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new seeMoreTypeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull seeMoreTypeViewHolder holder, int position) {
        Category category = categoryList.get(position);
        Glide.with(context).load(category.getCategoryImg())
                        .override(240,240).into(holder.binding.imageCategory);
        holder.binding.tvNameCategory.setText(category.getCategoryName());
        holder.binding.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickType.iClickType(category.getIdCategory());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public class seeMoreTypeViewHolder extends RecyclerView.ViewHolder {
        AllCategoryBinding binding;
        public seeMoreTypeViewHolder(@NonNull AllCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
