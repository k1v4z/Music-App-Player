package com.example.musicapp.model;

public class Album {
    private String idAlbum;
    private String albumName;
    private String albumAuthor;
    private String albumImage;

    public Album(String idAlbum, String albumName, String albumAuthor, String albumImage) {
        this.idAlbum = idAlbum;
        this.albumName = albumName;
        this.albumAuthor = albumAuthor;
        this.albumImage = albumImage;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumAuthor() {
        return albumAuthor;
    }

    public void setAlbumAuthor(String albumAuthor) {
        this.albumAuthor = albumAuthor;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }
}
