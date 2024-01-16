package com.example.musicapp.model;

import java.io.Serializable;

public class Advertisement implements Serializable {
    private String Id;
    private String Image;
    private String IdSong;
    private String NameSong;
    private String Rotate;

    public Advertisement(String id, String image, String idSong, String nameSong, String rotate) {
        Id = id;
        Image = image;
        IdSong = idSong;
        NameSong = nameSong;
        Rotate = rotate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getIdSong() {
        return IdSong;
    }

    public void setIdSong(String idSong) {
        IdSong = idSong;
    }

    public String getNameSong() {
        return NameSong;
    }

    public void setNameSong(String nameSong) {
        this.NameSong = nameSong;
    }

    public String getRotate() {
        return Rotate;
    }

    public void setRotate(String rotate) {
        Rotate = rotate;
    }
}
