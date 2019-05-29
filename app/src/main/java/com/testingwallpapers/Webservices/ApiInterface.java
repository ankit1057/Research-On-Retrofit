package com.testingwallpapers.Webservices;

import com.testingwallpapers.models.Collection;
import com.testingwallpapers.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("per_page") String per_page);
    @GET("collections/featured")
    Call<List<Collection>> getCollections(@Query("per_page") String per_page);

    @GET("collections/{id}")
    Call<Collection> getInformationOfCollection(@Path("id") int id);

    @GET("collections/{id}/photos")
    Call<List<Photo>> getPhotosOfCollection(@Path("id") int id, @Query("per_page") String per_page);

    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") String id);
}
