package com.example.testandroid.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testandroid.Global;
import com.example.testandroid.R;
import com.example.testandroid.databinding.AdapterDetailsBinding;
import com.example.testandroid.models.ContentDetails;

import java.util.List;

public class DetailDataAdapter extends RecyclerView.Adapter<DetailDataAdapter.MyViewHolder>{

    Context context;
    List<ContentDetails> contentDetailsList;
    DetailDataAdapterEvent detailDataAdapterEvent;

    public DetailDataAdapter(Context context, List<ContentDetails> contentDetailsList, DetailDataAdapterEvent detailDataAdapterEvent) {
        this.context = context;
        this.contentDetailsList = contentDetailsList;
        this.detailDataAdapterEvent = detailDataAdapterEvent;
    }
    public  interface DetailDataAdapterEvent
    {
        void openDetails(int itemAdapterPosition);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_details, parent, false);
        return new MyViewHolder(v);
    }

   /* public void loadData(List<ContentDetails> contentDetailsList) {
        this.contentDetailsList = contentDetailsList;
        notifyDataSetChanged();
    }*/

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContentDetails contentDetails = contentDetailsList.get(position);
        if(Global.IsNotNull(contentDetails))
        {
            if(Global.IsNotNull(contentDetails.getMediaTitleCustom())) {
                holder.binding.itemName.setText(contentDetails.getMediaTitleCustom());
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailDataAdapterEvent.openDetails(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (contentDetailsList.size() > 0) {
            return contentDetailsList.size();
        } else {
            return 0;
        }
    }

    public List<ContentDetails> getData() {
        return contentDetailsList;
    }

    public void removeItem(int position) {
        contentDetailsList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        AdapterDetailsBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = AdapterDetailsBinding.bind(itemView);

        }

    }
}
