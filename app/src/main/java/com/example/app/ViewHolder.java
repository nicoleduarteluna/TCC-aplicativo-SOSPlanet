package com.example.app;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {


    TextView rTitle, rdesc, rLoc, rObs, rSit;
    View mView;
    CardView cardView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        //Item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
        //item long click listener
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onLongClick(v, getAdapterPosition());
                return false;
            }
        });
        //initialize views with model_layout.xml
        rTitle = itemView.findViewById(R.id.rTitle);
        rdesc = itemView.findViewById(R.id.rdesc);
        rLoc = itemView.findViewById(R.id.rLoc);
        rObs = itemView.findViewById(R.id.robs);
        rSit = itemView.findViewById(R.id.rsit);
        cardView = itemView.findViewById(R.id.cardView);
        cardView.setOnCreateContextMenuListener(this);

    }
    private ViewHolder.ClickListener mClickListener;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo contextMenuInfo) {
    }

//interface for click listener
public interface ClickListener{
    void onItemClick(View view, int position);
    void onLongClick(View view, int position);
}
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
