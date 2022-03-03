package com.example.covidpersonaltracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.covidpersonaltracker.databinding.ActivityHomeBinding;
import com.example.covidpersonaltracker.fragment.HomeFragment;
import com.example.covidpersonaltracker.fragment.NearbyFragment;
import com.example.covidpersonaltracker.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Making Toolbar as Action Bar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Drawer Toggle (Hamburger Icon)
        drawerToggle=new ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.open,R.string.close);
        drawerToggle.syncState();
        binding.drawerLayout.addDrawerListener(drawerToggle);


        //Bottom Navigation
        callFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item->{
            switch (item.getItemId()){
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


//        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                return false;
//            }
//        });
    }
    private void callFragment(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }

}