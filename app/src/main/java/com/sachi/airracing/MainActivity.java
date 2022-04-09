package com.sachi.airracing;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;

import com.sachi.airracing.Util.Sp;
import com.sachi.airracing.databinding.ActivityMainBinding;
import com.sachi.airracing.ui.LoginFragment;
import com.sachi.airracing.ui.home.HomeFragment;
import com.sachi.airracing.ui.slideshow.ReservationFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        Menu menu = binding.navView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.nav_Login);
        menuItem.setTitle(Sp.getShared(this,"login","login"));

        toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.appBarMain.toolbar,R.string.Open,R.string.Close);
        toggle.syncState();

        callFragment(new HomeFragment());
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        callFragment(new HomeFragment());
                        binding.drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_Login:
                        callFragment(new LoginFragment());
                        binding.drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_Reservation:
                        callFragment(new ReservationFragment());
                        binding.drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }


        });

    }


    //To Call Fragments
    public void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main,fragment);
        transaction.commit();
    }



}