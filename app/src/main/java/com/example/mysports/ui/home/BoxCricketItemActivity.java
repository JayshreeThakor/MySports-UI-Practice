package com.example.mysports.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mysports.R;
import com.example.mysports.adapter.CarouselAdapter;

import java.util.Arrays;
import java.util.List;

public class BoxCricketItemActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout dotsLayout;
    private ImageView[] dots;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_cricket_item);


        viewPager = findViewById(R.id.itemViewPager);
        dotsLayout = findViewById(R.id.itemDotsLayout);

        List<Integer> imageResources = Arrays.asList(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground
        );

        CarouselAdapter carouselAdapter = new CarouselAdapter(this, imageResources);
        viewPager.setAdapter(carouselAdapter);

        dots = new ImageView[imageResources.size()];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
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
    }
}