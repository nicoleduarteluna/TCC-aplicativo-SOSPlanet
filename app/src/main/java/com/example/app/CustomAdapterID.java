package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapterID extends RecyclerView.Adapter<ViewHolderID> {
    List<ModelID> modelList;

    public CustomAdapterID(List<ModelID> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolderID onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model_id_layout, viewGroup, false);

        ViewHolderID viewHolder = new ViewHolderID(itemView);
        viewHolder.setOnClickListener(new ViewHolderID.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String Title = modelList.get(position).getTitle();
                String Loc   = modelList.get(position).getLoc();
                String Desc  = modelList.get(position).getDesc();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderID viewholder, int i) {
        viewholder.rTitle.setText(modelList.get(i).getTitle());
        viewholder.rLoc.setText(modelList.get(i).getLoc());
        viewholder.rdesc.setText(modelList.get(i).getDesc());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
