package com.example.covidpersonaltracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.covidpersonaltracker.databinding.ActivityHomeBinding;
import com.example.covidpersonaltracker.fragment.HomeFragment;
import com.example.covidpersonaltracker.fragment.NearbyFragment;
import com.example.covidpersonaltracker.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    ActionBarDrawerToggle drawerToggle;
    ImageView headerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View Binding
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //Making Toolbar as Action Bar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Drawer Toggle (Hamburger Icon)
        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        binding.drawerLayout.addDrawerListener(drawerToggle);

        //ON item click of side Navigation
        binding.sideNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        callFragment(new HomeFragment());
                        binding.drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_nearby:
                        callFragment(new NearbyFragment());
                        binding.drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_profile:
                        callFragment(new ProfileFragment());
                        binding.drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });


        //Bottom Navigation
        callFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    callFragment(new HomeFragment());
                    break;
                case R.id.nav_nearby:
                    callFragment(new NearbyFragment());
                    break;
                case R.id.nav_profile:
                    callFragment(new ProfileFragment());
                    break;
            }
            return true;
        });

        //inflate Header Layout
        View view =binding.sideNavigationView.inflateHeaderView(R.layout.header_layout);
        headerImage=view.findViewById(R.id.header_image_view);
        headerImage.setOnClickListener(item->{
            Toast.makeText(this, "Header Image Clicked", Toast.LENGTH_SHORT).show();
        });

        Toast.makeText(this, Utils.getSharedPreferences(this,"Settings","name"), Toast.LENGTH_SHORT).show();
    }

    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

}