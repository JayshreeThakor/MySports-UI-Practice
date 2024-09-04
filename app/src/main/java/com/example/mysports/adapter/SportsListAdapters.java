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

/*public class SportsListAdapters extends RecyclerView.Adapter<SportsListAdapters.SportsViewHolder> {

    private final Context context;
    private final List<Integer> list;

    public SportsListAdapters(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for the RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_sports_list, parent, false);
        return new SportsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
        // Bind the data to the view holder
        holder.imageView.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class SportsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public SportsViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the ImageView
            imageView = itemView.findViewById(R.id.itemImageview);
        }
    }
}*/


public class SportsListAdapters extends RecyclerView.Adapter<SportsListAdapters.SportsViewHolder> {

    private final Context context;
    private final List<Integer> imageResources;

    public SportsListAdapters(Context context, List<Integer> imageResources) {
        this.context = context;
        this.imageResources = imageResources;
    }

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_recycler_sports_list, parent, false);
        return new SportsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
        holder.imageView.setImageResource(imageResources.get(position));
    }

    @Override
    public int getItemCount() {
        return imageResources.size();
    }

    public static class SportsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public SportsViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImageview);
            imageView.setClipToOutline(true);
        }
    }
}
