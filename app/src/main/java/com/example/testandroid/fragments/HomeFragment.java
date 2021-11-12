package com.example.testandroid.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testandroid.ApiClient;
import com.example.testandroid.Global;
import com.example.testandroid.R;
import com.example.testandroid.SwipeToDeleteCallback;
import com.example.testandroid.adapters.DetailDataAdapter;
import com.example.testandroid.databinding.FragmentHomeBinding;
import com.example.testandroid.interfaces.ApiInterface;
import com.example.testandroid.models.CommonData;
import com.example.testandroid.models.ContentDetails;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    FragmentHomeBinding binding;
    List<ContentDetails> contentDetailsList = null;
    DetailDataAdapter detailDataAdapter = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        initView();
        return binding.getRoot();
    }

    private void initView() {
        if(contentDetailsList == null) {
            contentDetailsList = new ArrayList<>();
        }

        detailDataAdapter = new DetailDataAdapter(getActivity(), contentDetailsList,detailDataAdapterEvent);
        binding.rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvData.setAdapter(detailDataAdapter);
        enableSwipeToDelete();
        if(contentDetailsList.size() == 0) {
            getDetailList();
        }

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            getDetailList();

        });

    }

    public void getDetailList()
    {
        binding.swipeRefreshLayout.setRefreshing(true);
        new getData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void enableSwipeToDelete() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getActivity()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final ContentDetails item = detailDataAdapter.getData().get(position);

                detailDataAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(binding.rlLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(binding.rvData);
    }


    DetailDataAdapter.DetailDataAdapterEvent detailDataAdapterEvent = new DetailDataAdapter.DetailDataAdapterEvent() {
        @Override
        public void openDetails(int itemAdapterPosition) {
          Fragment fragment = new ExpandDetails();
          Bundle bundle = new Bundle();
          bundle.putSerializable("Details",contentDetailsList.get(itemAdapterPosition));
          fragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.mainContainer,fragment).addToBackStack(HomeFragment.TAG).commit();

        }

    };

    private class getData extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

                Call<CommonData> apiResponseCall = apiInterface.getData();

                apiResponseCall.enqueue(new Callback<CommonData>() {
                    @Override
                    public void onResponse(Call<CommonData> call, Response<CommonData> response) {

                        //progressBar.setVisibility(View.GONE);
                        if (binding.swipeRefreshLayout.isRefreshing()) {
                            binding.swipeRefreshLayout.setRefreshing(false);
                        }

                        if (Global.IsNotNull(response.body())) {


                            List<ContentDetails> apicontentDetailsList = response.body().getContent();

                            if (Global.IsNotNull(apicontentDetailsList)) {

                                contentDetailsList.clear();
                                contentDetailsList.addAll(apicontentDetailsList);

                                if (contentDetailsList.size() > 0) {
                                    detailDataAdapter.notifyDataSetChanged();
                                }
                            }

                        } else if (Global.IsNotNull(response.errorBody())) {
                            if (binding.swipeRefreshLayout.isRefreshing()) {
                                binding.swipeRefreshLayout.setRefreshing(false);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<CommonData> call, Throwable t) {
                        if (binding.swipeRefreshLayout.isRefreshing()) {
                            binding.swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
            } catch (Exception e) {
                e.getMessage();
                if (binding.swipeRefreshLayout.isRefreshing()) {
                    binding.swipeRefreshLayout.setRefreshing(false);
                }

            }


            return null;
        }
    }
}