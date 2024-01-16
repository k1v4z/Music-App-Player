package com.example.musicapp.service;

import com.example.musicapp.home.HomeActivity;
import com.example.musicapp.model.Advertisement;
import com.example.musicapp.model.Album;
import com.example.musicapp.model.ArtistOfYear;
import com.example.musicapp.model.Category;
import com.example.musicapp.model.RegisterResponse;
import com.example.musicapp.model.Song;
import com.example.musicapp.model.State;
import com.example.musicapp.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("AddUsers.php")
    Call<RegisterResponse> register(@Field("UserName") String UserName,
                                    @Field("Passwords") String Passwords,
                                    @Field("Playlist") String Playlist);
    @FormUrlEncoded
    @POST("Login.php")
    Call<State> login(@Field("UserName") String UserName,
                      @Field("Passwords") String passwords);
    @GET("getAdvertisement.php")
    Call<List<Advertisement>> getListAdvertisement();
    @GET("getArtist.php")
    Call<List<ArtistOfYear>> getListArtist();
    @GET("songMaybeLike.php")
    Call<List<Song>> getListSongMayBeLike();
    @GET("getAlbum.php")
    Call<List<Album>> getListAlbum();
    @GET("getAllAlbum.php")
    Call<List<Album>> getAllListAlbum();
    @GET("getCategory.php")
    Call<List<Category>> getAllListCategory();
    @FormUrlEncoded
    @POST("songAdvertise.php")
    Call<List<Song>> getListAdvertisementSong(@Field("idSong") String idSong);
    @FormUrlEncoded
    @POST("Search.php")
    Call<List<Song>> searchSong(@Field("key") String key);
    @FormUrlEncoded
    @POST("getSongCategory.php")
    Call<List<Song>> getListCategorySong(@Field("categoryName") String categoryName);
    @FormUrlEncoded
    @POST("getSongAlbum.php")
    Call<List<Song>> getListAlbumSong(@Field("idAlbum") String idAlbum);
    @FormUrlEncoded
    @POST("getInformation.php")
    Call<List<Users>> getInformation(@Field("UserName") String UserName);
    @FormUrlEncoded
    @POST("updateInformation.php")
    Call<State> updateInformation(@Field("fullName") String fullName,
                                  @Field("date") String date,
                                  @Field("userName") String userName);
    @FormUrlEncoded
    @POST("ChangePassword.php")
    Call<State> changePassword(@Field("userName") String userName,
                               @Field("oldPassword") String oldPassword,
                               @Field("newPassword") String newPassword);
    @FormUrlEncoded
    @POST("playlist.php")
    Call<State> upPlayListToServer(@Field("Json") String json,
                                   @Field("userName") String userName);
    @FormUrlEncoded
    @POST("getPlaylist.php")
    Call<List<Users>> getPlaylist(@Field("userName") String userName);
}
