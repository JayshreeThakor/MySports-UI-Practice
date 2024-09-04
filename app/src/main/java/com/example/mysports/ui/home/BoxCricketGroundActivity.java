package com.example.mysports.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysports.R;
import com.example.mysports.adapter.SportIsBoxAdapter;

import java.util.Arrays;
import java.util.List;

public class BoxCricketGroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_cricket_ground);

        RecyclerView recyclerView2 = findViewById(R.id.botCricketList);

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

        SportIsBoxAdapter sportIsBoxAdapter = new SportIsBoxAdapter(this, imageResources2, position -> {
            Intent intent = new Intent(this, BoxCricketItemActivity.class);
            startActivity(intent);
        },100);
        recyclerView2.setAdapter(sportIsBoxAdapter);

    }
}