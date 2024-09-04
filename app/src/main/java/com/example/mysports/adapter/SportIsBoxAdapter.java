package com.example.mysports.adapter;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysports.R;

import java.util.List;


public class SportIsBoxAdapter extends RecyclerView.Adapter<SportIsBoxAdapter.ImageViewHolder> {

    private final Context context;
    private final List<Integer> imageResources;
    private OnItemClickListener onItemClickListener;
    private int itemType;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public SportIsBoxAdapter(Context context, List<Integer> imageResources, OnItemClickListener onItemClickListener, int itemType) {
        this.context = context;
        this.imageResources = imageResources;
        this.onItemClickListener = onItemClickListener;
        this.itemType = itemType;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (itemType == 0) {
            View view = inflater.inflate(R.layout.layout_recycler_box, parent, false);
            return new ImageViewHolder(view, onItemClickListener);
        } else {
            View view = inflater.inflate(R.layout.layout_recycler_box_full, parent, false);
            return new ImageViewHolder(view, onItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.imageView.setImageResource(imageResources.get(position));
    }

    @Override
    public int getItemCount() {
        return imageResources.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycle_image_2);
            imageView.setClipToOutline(true);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}