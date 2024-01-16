package com.example.musicapp.model;

public class Category {
    private String idCategory;
    private String categoryName;
    private String categoryImg;

    public Category(String idCategory, String categoryName, String categoryImg) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.categoryImg = categoryImg;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }
}
