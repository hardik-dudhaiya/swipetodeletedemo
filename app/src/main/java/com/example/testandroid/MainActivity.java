package com.example.testandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.testandroid.databinding.ActivityMainBinding;
import com.example.testandroid.fragments.DetailsFragment;
import com.example.testandroid.fragments.HomeFragment;
import com.example.testandroid.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.bottomNavigationView.setOnItemSelectedListener(bottomViewItemSelectedListener);
        activityMainBinding.bottomNavigationView.setSelectedItemId(R.id.home);
    }

    NavigationBarView.OnItemSelectedListener bottomViewItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.home:
                        openFragment(new HomeFragment(),HomeFragment.TAG);
                    break;
                case R.id.details:
                    openFragment(new DetailsFragment(), DetailsFragment.TAG);
                    break;
                case R.id.profile:
                    openFragment(new ProfileFragment(), ProfileFragment.TAG);
                    break;
            }

            return false;
        }
    };


     public void openFragment(Fragment fragement, String tag)
     {
         FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
         fragmentTransaction.replace(R.id.mainContainer, fragement, tag);
         fragmentTransaction.addToBackStack(null);
         fragmentTransaction.commitAllowingStateLoss();

     }
}