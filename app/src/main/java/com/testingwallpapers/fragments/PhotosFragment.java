package com.testingwallpapers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.testingwallpapers.R;
import com.testingwallpapers.Webservices.ApiInterface;
import com.testingwallpapers.Webservices.ServiceGenerator;
import com.testingwallpapers.adapters.PhotosAdapter;
import com.testingwallpapers.models.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment {
    private final String TAG = PhotosFragment.class.getSimpleName();

    @BindView(R.id.fragment_photos_processbar)
    ProgressBar progressBar;
    @BindView(R.id.fragment_photos_recyclerview)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    private PhotosAdapter photosAdapter;
    private List<Photo> photos = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_photos, container, false);
        unbinder = ButterKnife.bind(this, view);
        ButterKnife.bind(view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        photosAdapter = new PhotosAdapter(getActivity(), photos);
        recyclerView.setAdapter(photosAdapter);

        showProgressBar(true);
        getPhotos();

        return view;
    }

    private void getPhotos() {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Photo>> call = apiInterface.getPhotos("30" );
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    photos.addAll(response.body());
                    //photosAdapter.notifyDataSetChanged();
                    photosAdapter = new PhotosAdapter(getActivity(), photos);
                    recyclerView.setAdapter(photosAdapter);
                } else {
                    Log.e(TAG, "failure " + response.message());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e(TAG, "failure " + t.toString());
                showProgressBar(false);
            }
        });
    }

    private void showProgressBar(boolean isShown) {
        if(isShown){
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // unbinder.unbind();
    }
}
