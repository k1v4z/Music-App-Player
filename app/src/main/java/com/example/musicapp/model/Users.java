package com.example.musicapp.model;

public class Users {
    String idUser;
    String userName;
    String passWord;
    String playList;
    String fullName;
    String dateOfBirth;
    String avatar;

    public Users(String idUser, String userName, String passWord, String playList,
                 String fullName, String dateOfBirth, String avatar) {
        this.idUser = idUser;
        this.userName = userName;
        this.passWord = passWord;
        this.playList = playList;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPlayList() {
        return playList;
    }

    public void setPlayList(String playList) {
        this.playList = playList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
