package com.example.mysports.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mysports.R;
import com.example.mysports.ui.fragment.HomeFragment;
import com.example.mysports.ui.fragment.ProfileFragment;
import com.example.mysports.ui.fragment.TournamentFragment;
import com.example.mysports.ui.fragment.UmpireFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        LoadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            LoadFragment(new HomeFragment());
                            break;
                        case R.id.navigation_tournament:
                            LoadFragment(new TournamentFragment());
                            break;
                        case R.id.navigation_umpire:
                            LoadFragment(new UmpireFragment());
                            break;
                        case R.id.navigation_profile:
                            LoadFragment(new ProfileFragment());
                            break;
                    }
                    return true;
                }
        );
    }
    private void LoadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}