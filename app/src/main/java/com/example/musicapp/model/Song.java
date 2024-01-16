package com.example.musicapp.model;

import java.io.Serializable;

public class Song implements Serializable {
    private String idSong;
    private String idAlbum;
    private String idCategory;
    private String nameSong;
    private String author;
    private String thumb;
    private String rotate;
    private String linkSong;
    public Song(String idSong, String idAlbum, String idCategory, String nameSong
                , String author, String thumb, String rotate, String linkSong) {
        this.idSong = idSong;
        this.idAlbum = idAlbum;
        this.idCategory = idCategory;
        this.nameSong = nameSong;
        this.author = author;
        this.thumb = thumb;
        this.rotate = rotate;
        this.linkSong = linkSong;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }
}
