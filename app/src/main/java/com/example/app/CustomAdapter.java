package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<Model> modelList;

    public CustomAdapter(List<Model> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String Title = modelList.get(position).getTitle();
                String Loc   = modelList.get(position).getLoc();
                String Desc  = modelList.get(position).getDesc();
                String obs   = modelList.get(position).getObs();
                String sit   = modelList.get(position).getSit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int i) {
        viewholder.rTitle.setText(modelList.get(i).getTitle());
        viewholder.rLoc.setText(modelList.get(i).getLoc());
        viewholder.rdesc.setText(modelList.get(i).getDesc());
        viewholder.rObs.setText(modelList.get(i).getObs());
        viewholder.rSit.setText(modelList.get(i).getSit());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
