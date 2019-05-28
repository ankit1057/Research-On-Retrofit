package com.testingwallpapers.Webservices;

import com.testingwallpapers.models.Collection;
import com.testingwallpapers.models.Photo;
import com.testingwallpapers.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("per_page") String per_page);
    @GET("collections/featured")
    Call<List<Collection>> getCollections();

    @GET("collections/{id}")
    Call<Collection> getInformationOfCollection(@Path("id") int id);
}
