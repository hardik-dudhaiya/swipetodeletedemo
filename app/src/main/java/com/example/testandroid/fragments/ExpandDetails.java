package com.example.testandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.testandroid.Global;
import com.example.testandroid.databinding.FragmentExpanddetailsBinding;
import com.example.testandroid.databinding.FragmentHomeBinding;
import com.example.testandroid.models.ContentDetails;

public class ExpandDetails extends Fragment {

    public static final String TAG = "ExpandDetails";

    FragmentExpanddetailsBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentExpanddetailsBinding.inflate(getLayoutInflater());

        initView();
        return binding.getRoot();
    }

    private void initView() {

      ContentDetails contentDetails = (ContentDetails) getArguments().getSerializable("Details");
      if(Global.IsNotNull(contentDetails)) {
          if(Global.IsNotNull(contentDetails.getMediaTitleCustom())) {
              binding.title.setText(contentDetails.getMediaTitleCustom());
          }
          if(Global.IsNotNull(contentDetails.getMediaDate().getDateString())) {
              binding.date.setText(contentDetails.getMediaDate().getDateString());
          }
          if(Global.IsNotNull(contentDetails.getMediaUrl())) {
              binding.link.setText(contentDetails.getMediaUrl());
          }
      }
    }
}
