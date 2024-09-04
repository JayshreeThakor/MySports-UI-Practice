package com.example.mysports.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mysports.R;
import com.example.mysports.adapter.CarouselAdapter;
import com.example.mysports.adapter.SportIsBoxAdapter;
import com.example.mysports.adapter.SportsListAdapters;
import com.example.mysports.ui.home.BoxCricketGroundActivity;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private LinearLayout dotsLayout;
    private ImageView[] dots;
    private Handler handler;
    private Runnable runnable;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPager);
        dotsLayout = view.findViewById(R.id.dotsLayout);
        RecyclerView recyclerView = view.findViewById(R.id.recycleLayout);
        RecyclerView recyclerView2 = view.findViewById(R.id.recycleLayout2);
        LinearLayout textViewAll = view.findViewById(R.id.txtViewAll);

        List<Integer> imageResources = Arrays.asList(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background
        );

        CarouselAdapter carouselAdapter = new CarouselAdapter(getContext(), imageResources);
        viewPager.setAdapter(carouselAdapter);

        dots = new ImageView[imageResources.size()];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageResource(R.drawable.ic_dot_inactive);
            dotsLayout.addView(dots[i]);
        }

        dots[0].setImageResource(R.drawable.ic_dot_active);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (ImageView dot : dots) {
                    dot.setImageResource(R.drawable.ic_dot_inactive);
                }
                dots[position].setImageResource(R.drawable.ic_dot_active);
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = (currentItem + 1) % dots.length;
                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(this, 2000);
            }
        };

        List<Integer> imageResources2 = Arrays.asList(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background
        );

        SportsListAdapters sportsListAdapters = new SportsListAdapters(getContext(), imageResources2);
        recyclerView.setAdapter(sportsListAdapters);

        textViewAll.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), BoxCricketGroundActivity.class);
            startActivity(intent);
        });

        SportIsBoxAdapter sportIsBoxAdapter = new SportIsBoxAdapter(getContext(), imageResources2, position -> {
        }, 0);
        recyclerView2.setAdapter(sportIsBoxAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}