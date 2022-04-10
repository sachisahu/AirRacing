package com.sachi.airracing;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;

import com.sachi.airracing.Util.Database;
import com.sachi.airracing.Util.Sp;
import com.sachi.airracing.databinding.ActivityMainBinding;
import com.sachi.airracing.ui.LoginFragment;
import com.sachi.airracing.ui.home.HomeFragment;
import com.sachi.airracing.ui.slideshow.ReservationFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    ActionBarDrawerToggle toggle;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(this);

        setSupportActionBar(binding.appBarMain.toolbar);



        toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.appBarMain.toolbar,R.string.Open,R.string.Close);
        toggle.syncState();

        String sharedP = Sp.getShared(this,"login","loginPhone");
        if(sharedP.length()>0){
            Menu menu1 = binding.navView.getMenu();
            MenuItem reservation = menu1.findItem(R.id.nav_Reservation);
            reservation.setVisible(true);
            MenuItem menuItemLogout = menu1.findItem(R.id.nav_logout);
            menuItemLogout.setVisible(true);
            MenuItem menuItemLogin = menu1.findItem(R.id.nav_Login);
            menuItemLogin.setVisible(false);
            MenuItem menuItemSignup = menu1.findItem(R.id.nav_signup);
            menuItemSignup.setVisible(false);
        }else {

            Menu menu1 = binding.navView.getMenu();
            MenuItem reservation = menu1.findItem(R.id.nav_Reservation);
            reservation.setVisible(false);
            MenuItem menuItemLogout = menu1.findItem(R.id.nav_logout);
            menuItemLogout.setVisible(false);
        }
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
                    case R.id.nav_signup:
                        callFragment(new LoginFragment());
                        binding.drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_Reservation:
                        callFragment(new ReservationFragment());
                        binding.drawerLayout.closeDrawers();
                        db.featch();
                        break;
                    case R.id.nav_logout:
                        logout();
                        binding.drawerLayout.closeDrawers();
                        break;

                }
                return false;
            }

            private void logout() {
                Sp.clearSp(MainActivity.this,"login");
                Menu menu1 = binding.navView.getMenu();
                MenuItem menuItemLogin = menu1.findItem(R.id.nav_Login);
                menuItemLogin.setVisible(true);
                MenuItem menuItemSignup = menu1.findItem(R.id.nav_signup);
                menuItemSignup.setVisible(true);
                MenuItem reservation = menu1.findItem(R.id.nav_Reservation);
                reservation.setVisible(false);
                MenuItem menuItemLogout = menu1.findItem(R.id.nav_logout);
                menuItemLogout.setVisible(false);

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